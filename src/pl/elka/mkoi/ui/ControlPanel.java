package pl.elka.mkoi.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigInteger;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import org.apache.log4j.Logger;

import pl.elka.mkoi.exception.DiscreteLogarithmException;
import pl.elka.mkoi.exception.FormValidationFailedException;
import pl.elka.mkoi.exception.MathException;
import pl.elka.mkoi.exception.SolutionNotExistException;
import pl.elka.mkoi.logger.CustomConsoleLogger;
import pl.elka.mkoi.math.BabyStepGiantStep;
import pl.elka.mkoi.utils.ValidatorUtil;

/**
 * Control panel, where buttons and text fields are placed. It is most interactive place in application.
 * User uses this panel's objects to set calculation parameters and run calculation.
 *
 */
public class ControlPanel extends JPanel {
	
	/**
	 * Log4J logger
	 */
	private static Logger LOG = Logger.getLogger(ControlPanel.class);
	
	private static final long serialVersionUID = 1L;

	/**
	 * Panel width - {@value}px
	 */
	static final int WIDTH = 240;
	
	/**
	 * Button to run calculations.
	 */
	private JButton calculateButton;
	/**
	 * Button to clear {@link ConsolePanel}.
	 */
	private JButton clearConsoleButton;
	
	/**
	 * {@link BigIntegerTextField} where user can specify logarithm base (g) used in calculation.
	 */
	private BigIntegerTextField logBase;
	/**
	 * {@link BigIntegerTextField} where user can specify modulus (p) used in calculation.
	 */
	private BigIntegerTextField modulus;
	/**
	 * {@link BigIntegerTextField} where user can specify result (x) used in calculation.
	 */
	private BigIntegerTextField result;
	
	/**
	 * Checkbox indicating, whether calculation details should be logged on {@link ConsolePanel}
	 */
	private JCheckBox showCalculation;
	
	/**
	 * {@link StatusPanel}, on which current application status is being shown.
	 */
	private final StatusPanel statusPanel;
	/**
	 * {@link ConsolePanel}, on which calculations are logged.
	 */
	private final ConsolePanel consolePanel;
	
	/**
	 * {@link BabyStepGiantStep} class instance used to calculate discrete logarithm problem.
	 */
	private final BabyStepGiantStep discreteLogarithm = new BabyStepGiantStep(new CustomConsoleLogger(BabyStepGiantStep.class));;
	
	/**
	 * Constructs the panel.
	 */
	public ControlPanel() {
		statusPanel = StatusPanel.getInstance();
		consolePanel = ConsolePanel.getInstance();
		setInfoLabel();
		showCalculation = new JCheckBox();
		showCalculation.setText("Show calculations");
		showCalculation.setPreferredSize(DefaultSizes.TEXTFIELD.getDimension());
		showCalculation.setToolTipText("can cause major application slowdown");
		showCalculation.addActionListener((e) -> {
			discreteLogarithm.setShowCalculation(showCalculation.isSelected());
		});
		this.setVisible(true);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		this.setPreferredSize(new Dimension(WIDTH,0));
		logBase = new BigIntegerTextField(true);
		logBase.setPreferredSize(DefaultSizes.TEXTFIELD.getDimension());
		modulus = new BigIntegerTextField(false);
		modulus.setPreferredSize(DefaultSizes.TEXTFIELD.getDimension());
		result = new BigIntegerTextField(false);
		result.setPreferredSize(DefaultSizes.TEXTFIELD.getDimension());
		this.add(new JLabel("g = "));
		this.add(logBase);
		this.add(new JLabel("p = "));
		this.add(modulus);
		this.add(new JLabel("x = "));
		this.add(result);
		this.add(showCalculation);
		
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener((e) -> {
			try {
				if (! ValidatorUtil.validateForms(logBase, modulus, result)) {
					throw new FormValidationFailedException();
				} else {
					LOG.debug("\nStarting discrete logarithm calculation...");
					statusPanel.setBusy();
					calculateButton.setEnabled(false);
					new Thread(() -> {						
						boolean errorOccured = true;
						
						try { 
							BigInteger solution = (BigInteger) discreteLogarithm.calculate(logBase.getValue(), 
										modulus.getValue(), result.getValue());
							LOG.debug("Solution FOUND!: " + solution.intValue());
							statusPanel.setReady("last solution: "+solution.intValue());
							errorOccured = false;
						} catch (SolutionNotExistException ex) {
							LOG.warn(ex.getMessage());
							statusPanel.setReady();
							errorOccured = false;
						} catch (DiscreteLogarithmException ex) {
							LOG.warn(ex.getMessage());
						} catch (MathException ex) {
							LOG.error(ex.getMessage());
						} finally {
							calculateButton.setEnabled(true);
						}
						if (errorOccured) {
							statusPanel.setError();
						}
						
					}).start();
				}
			} catch (FormValidationFailedException ex) {
				LOG.warn(ex.getMessage());
			}
		}); 
		
		clearConsoleButton = new JButton("Clear console");
		clearConsoleButton.addActionListener((e) -> {
			consolePanel.clear();
		});
		this.add(calculateButton);
		this.add(clearConsoleButton);
	}
	
	/**
	 * Add information label on the top of this panel.
	 */
	private void setInfoLabel() {
		JLabel infoLabel = new JLabel();
		infoLabel.setPreferredSize(new Dimension(WIDTH - 20, 100));
		infoLabel.setForeground(Color.BLACK);
		StringBuilder sb = new StringBuilder("<html>");
		sb.append("This algorithm is used to solve discrete logarithm problem.<br />");
		sb.append("Having <i>x, g, p</i> we have to calculate <i>a</i>, ");
		sb.append("which are given in following equation:<br/>");
		sb.append("log<sub>g</sub>x = a (mod p)");
		sb.append("</html>");
		infoLabel.setText(sb.toString());
		this.add(infoLabel);
	}
}
