import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.regex.*;

/** Standalone Swing GUI application for demonstrating REs.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version #Id$
 */
public class REDemo extends JPanel {
	Pattern pattern;
	Matcher matcher;
	JTextField patternTF, stringTF;
	JCheckBox compiledOK;
	JRadioButton match, find, findAll;
	JTextField matchesTF;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		final JFrame f = new JFrame("REDemo");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		REDemo comp = new REDemo();
		f.setContentPane(comp);
		f.pack();
		f.setLocation(200, 200);
		f.setVisible(true);
	}

	/** Construct the REDemo object including its GUI */
	public REDemo() {
		super();

		JPanel top = new JPanel();
		top.add(new JLabel("Pattern:", JLabel.RIGHT));
		patternTF = new JTextField(20);
		patternTF.getDocument().addDocumentListener(new PatternListener());
		top.add(patternTF);
		compiledOK = new JCheckBox("Syntax OK?");
		top.add(compiledOK);

		JPanel switchPane = new JPanel();
		ButtonGroup bg = new ButtonGroup();
		match = new JRadioButton("Match");
		match.setSelected(true);
		bg.add(match);
		switchPane.add(match);
		find = new JRadioButton("Find");
		bg.add(find);
		switchPane.add(find);
		findAll = new JRadioButton("Find All");
		bg.add(findAll);
		switchPane.add(findAll);

		JPanel strPane = new JPanel();
		strPane.add(new JLabel("String:", JLabel.RIGHT));
		stringTF = new JTextField(20);
		stringTF.getDocument().addDocumentListener(new StringListener());
		strPane.add(stringTF);
		strPane.add(new JLabel("Matches:"));
		matchesTF = new JTextField(3);
		strPane.add(matchesTF);

		setLayout(new GridLayout(3, 1, 5, 5));
		add(top);
		add(strPane);
		add(switchPane);
	}

	void setMatches(boolean b) {
		if (b)
			matchesTF.setText("Yes");
		else
			matchesTF.setText("No");
	}

	void setMatches(int n) {
		matchesTF.setText(Integer.toString(n));
	}

	void tryCompile() {
		pattern = null;
		try {
			pattern = Pattern.compile(patternTF.getText());
			matcher = pattern.matcher("");
			compiledOK.setSelected(true);
		} catch (PatternSyntaxException ex) {
			compiledOK.setSelected(false);
		}
	}

	void tryMatch() {
		if (pattern == null)
			return;
		matcher.reset(stringTF.getText());
		if (match.isSelected() && matcher.matches()) {
			setMatches(true);
			return;
		}
		if (find.isSelected() && matcher.find()) {
			setMatches(true);
			return;
		}
		if (findAll.isSelected()) {
			int i = 0;
			while (matcher.find()) {
				++i;
			}
			if (i > 0) {
				setMatches(i);
				return;
			}
		}
		setMatches(false);
	}

	/** Any change to the pattern tries to compile the result. */
	class PatternListener implements DocumentListener {

		public void changedUpdate(DocumentEvent ev) {
			tryCompile();
		}

		public void insertUpdate(DocumentEvent ev) {
			tryCompile();
		}

		public void removeUpdate(DocumentEvent ev) {
			tryCompile();
		}
	}

	/** Any change to the input string tries to match the result */
	class StringListener implements DocumentListener {


		public void changedUpdate(DocumentEvent ev) {
			tryMatch();
		}

		public void insertUpdate(DocumentEvent ev) {
			tryMatch();
		}

		public void removeUpdate(DocumentEvent ev) {
			tryMatch();
		}
	}
}
