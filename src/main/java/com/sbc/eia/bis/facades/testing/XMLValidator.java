package com.sbc.eia.bis.facades.testing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 */
public class XMLValidator implements ActionListener {

	// Character limit for the text area
	public static final int MAXTEXTLENGTH = 4096;

	// GUI components
	private JFrame _frame;
	private JCheckBox _isFullSchemaCheckingEnabled = new JCheckBox("Enable full schema checking");
	private JCheckBox _isNamespaceEnabled = new JCheckBox("Enable namespaces");
	private JPanel _selectionPane;
	private static JTextArea _txaValidationErrors = new JTextArea();
	private JTextField _txtFileName;
	private JTextField _txtSchemaName;
	private JTextField _txtStatus;

	// Selection panel insets
	private final int _H_INSET = 10;
	private final int _V_INSET = 4;

	// Error handler that provides callback methods to the parser
	private final static class Handler extends DefaultHandler {
		public boolean hasError = false;
		public void error(SAXParseException e) throws SAXException {
			updateTextArea("ERROR: Encountered " + e.getClass().getName() + ": " + e.getMessage());
			hasError = true;
		}
		public void fatalError(SAXParseException e) throws SAXException {
			updateTextArea("FATAL ERROR: Encountered " + e.getClass().getName() + ": " + e.getMessage());
			hasError = true;
		}
		public void warning(SAXParseException e) throws SAXException {
			updateTextArea("WARNING: Encountered " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	*/
	public void actionPerformed(ActionEvent we) {

		// Change the cursor
		_frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		_txaValidationErrors.setText("");
		try {
			boolean isValid = false;
			try {
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser parser = factory.newSAXParser();

				Handler handler = new Handler();
				XMLReader reader = parser.getXMLReader();

				// Set parser features
				try {
					reader.setFeature("http://xml.org/sax/features/validation", true);
					reader.setFeature("http://apache.org/xml/features/validation/schema", true);
					if (_isNamespaceEnabled.isSelected()) {
						reader.setFeature("http://xml.org/sax/features/namespaces", true);
						reader.setProperty(
							"http://apache.org/xml/properties/schema/external-schemaLocation",
							_txtSchemaName.getText());
					} else {
						reader.setFeature("http://xml.org/sax/features/namespaces", false);
						reader.setProperty(
							"http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",
							_txtSchemaName.getText());
					}
					if (_isFullSchemaCheckingEnabled.isSelected()) {
						reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
					} else {
						reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", false);
					}
				} catch (SAXException e) {
					updateTextArea("Warning: Parser does not support schema validation");
				}

				parser.parse(_txtFileName.getText(), handler);
				isValid = !handler.hasError;
			} catch (Exception e) {
				updateTextArea("Encountered " + e.getClass().getName() + ": " + e.getMessage());
			}
			_txtStatus.setText("Validation result: " + isValid);
		} finally { // Restore the cursor
			_frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}

	/**
	*/
	private void initializeGUI() {

		// Instantiate the GUI members
		_frame = new JFrame("XMLValidator");
		_selectionPane = new JPanel(new GridBagLayout());
		_txtFileName = new JTextField("", 50);
		_txtSchemaName = new JTextField("", 50);
		_txtStatus = new JTextField();

		// Add a primary panel
		JPanel mainPane = new JPanel(new BorderLayout(), true);
		_frame.getContentPane().add(mainPane);

		// Add a selection panel
		mainPane.add(_selectionPane, BorderLayout.NORTH);

		// Establish the default gridbaglayout constraints
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.gridheight = 1;
		c.gridx = 0;
		c.insets = new Insets(_V_INSET, _H_INSET, _V_INSET, _H_INSET);
		c.weightx = 0.0;
		c.weighty = 0.0;

		// Create a panel for the file name
		JPanel filePane = new JPanel();
		filePane.add(new JLabel("XML file name:"), c);
		filePane.add(_txtFileName, c);
		c.gridy = 0;
		_selectionPane.add(filePane, c);

		// Create a panel for the schema name
		JPanel schemaPane = new JPanel();
		schemaPane.add(new JLabel("Schema name:"), c);
		schemaPane.add(_txtSchemaName, c);
		c.gridy = 1;
		_selectionPane.add(schemaPane, c);

		// Create a panel for the checkboxes
		JPanel checkboxPane = new JPanel();
		checkboxPane.add(_isNamespaceEnabled, c);
		checkboxPane.add(_isFullSchemaCheckingEnabled, c);
		c.gridy = 2;
		_selectionPane.add(checkboxPane, c);

		// Add a Validate button
		JButton btnValidate = new JButton("Validate");
		btnValidate.addActionListener(this);
		c.gridy = 3;
		_selectionPane.add(btnValidate, c);

		// Add a panel to display validation errors
		JScrollPane resultsPane = new JScrollPane(_txaValidationErrors);
		mainPane.add(resultsPane, BorderLayout.CENTER);

		// Add a status bar to display the validation result
		mainPane.add(_txtStatus, BorderLayout.SOUTH);
		_txtStatus.setEnabled(false);
	}

	/**
	 */
	private void launchGUI() {

		// Initialize the GUI components
		initializeGUI();

		// Set the frame's size
		_frame.pack();
		Insets i = _frame.getInsets();
		int frameHeight =
			i.top + i.bottom + _selectionPane.getPreferredSize().height + _txtStatus.getPreferredSize().height * 8;
		int frameWidth = i.left + i.right + _selectionPane.getPreferredSize().width;
		_frame.setSize(frameWidth, frameHeight);

		// Establish a listener for the window closing event
		_frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		_frame.setVisible(true);
	}

	/**
	 */
	public static void main(String[] args) {

		try {
			// Launch the GUI
			XMLValidator client = new XMLValidator();
			client.launchGUI();

			// Catch any exceptions that may have occurred
		} catch (Exception e) {
			System.err.println("Encountered " + e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/**
	 */
	public static void updateTextArea(String newText) {

		// Append new text to the current text area contents
		String text = _txaValidationErrors.getText() + System.getProperty("line.separator") + newText;

		// Check the text length
		int nLength = text.length();
		while (nLength > MAXTEXTLENGTH) {
			int nPos = text.indexOf(System.getProperty("line.separator"));
			if (nPos > -1 && nPos + 1 < nLength) {
				// Trim through the first line separator
				text = text.substring(nPos + 1);
			} else {
				// New text is too long; erase all text
				text = "(text overflow)";
			}
			nLength = text.length();
		}

		// Update the text area
		_txaValidationErrors.setText(text);
		_txaValidationErrors.setCaretPosition(nLength);
	}
}