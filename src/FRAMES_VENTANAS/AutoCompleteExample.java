package FRAMES_VENTANAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AutoCompleteExample extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTextField textField;
    private DefaultListModel<String> suggestionListModel;
	private JList<String> suggestionList;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoCompleteExample frame = new AutoCompleteExample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AutoCompleteExample() {
		frame = new JFrame("AutoComplete Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        suggestionListModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionListModel);
        suggestionList.setVisibleRowCount(5);
        JScrollPane scrollPane = new JScrollPane(suggestionList);
        
        textField = new JTextField();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });
        
        suggestionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedValue = suggestionList.getSelectedValue();
                    if (selectedValue != null) {
                        textField.setText(selectedValue);
                        hideSuggestions();
                    }
                }
            }
        });
        
        frame.getContentPane().add(textField, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void updateSuggestions() {
        String input = textField.getText().toLowerCase();
        suggestionListModel.clear();
        
        // Add matching elements to the suggestion list
        if (!input.isEmpty()) {
            // Perform your logic to retrieve matching elements based on the input
            // For simplicity, we'll use a predefined array of elements
            String[] elements = {"Apple", "Banana", "Orange", "Grapes", "Mango"};
            
            for (String element : elements) {
                if (element.toLowerCase().startsWith(input)) {
                    suggestionListModel.addElement(element);
                }
            }
        }
        
        boolean hasSuggestions = !suggestionListModel.isEmpty();
        suggestionList.setVisible(hasSuggestions);
        frame.revalidate();
    }
    
    private void hideSuggestions() {
        suggestionListModel.clear();
        suggestionList.setVisible(false);
        frame.revalidate();
    }
}
