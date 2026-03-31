package com.vincematolka.pizzacalculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PizzaCalculator extends JFrame{
    
    public PizzaCalculator(){
        setTitle("Pizza Seevings Calculator");
        JLabel title = new JLabel("Pizza Servings Calculator");
        title.setForeground(Color.RED);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);
        
        setLayout(new GridLayout(4,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        
        JPanel textSection = new JPanel();
        JLabel size = new JLabel("Enter the size of the pizza in inches: ");
        size.setFont(new Font("Arial", Font.BOLD, 13));
        textSection.add(size);
        JTextField sizeField = new JTextField(4);
        textSection.add(sizeField);
        add(textSection);
        
        JPanel button = new JPanel();
        JButton calcButton = new JButton("Calculate Servings");
        calcButton.setFont(new Font("Arial", Font.BOLD, 14));
        button.add(calcButton);
        button.setLayout(new GridLayout(1,1));
        add(button);
        
        JPanel servings = new JPanel();
        JLabel servingsLabel = new JLabel();
        servings.add(servingsLabel);
        add(servings);
        
        calcButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    double pizzaSize = Double.parseDouble(sizeField.getText());
                    double servingsValue = Math.pow(pizzaSize /8, 2);
                    servingsLabel.setText("Number of servings: " + String.format("%.2f", servingsValue));
                }
                catch(NumberFormatException ex){
                    servingsLabel.setText("invalid input. Enter a valid pizza size.");
                }
            }
        });
        setVisible(true);
    }
    
    public static void main(String[] args){
        new PizzaCalculator();
    }
    
}
