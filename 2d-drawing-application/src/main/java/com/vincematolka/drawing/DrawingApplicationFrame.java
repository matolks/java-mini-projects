package com.vincematolka.drawing;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawingApplicationFrame extends JFrame
{
    // Create the panels for the top of the application. One panel for each
    // line and one to contain both of those panels.
    
    JPanel panelOne = new JPanel();
    JPanel panelTwo = new JPanel();
    JPanel topPanel = new JPanel();

    // create the widgets for the firstLine Panel.
    JLabel shape = new JLabel("Shape:");
    String[] shapeStrings = {"Line", "Oval", "Rectangle"};
    JComboBox shapeList = new JComboBox(shapeStrings);
    JButton firstColor = new JButton();
    JLabel first = new JLabel("1st Color");
    JButton secondColor = new JButton();
    JLabel second = new JLabel("2nd Color");
    JButton undo = new JButton();
    JLabel undoLabel = new JLabel("Undo");
    JButton clear = new JButton();
    JLabel clearLabel = new JLabel("Clear");

    //create the widgets for the secondLine Panel.
    JLabel options = new JLabel("Options: ");
    JCheckBox filled = new JCheckBox("Filled");
    JCheckBox useGradient = new JCheckBox("Use Gradient");
    JCheckBox dashed = new JCheckBox("Dashed");
    JSpinner width = new JSpinner(new SpinnerNumberModel(10 , 1, 99, 1));
    JLabel widthLabel = new JLabel("Line Width");
    JSpinner length = new JSpinner(new SpinnerNumberModel(10 , 1, 99, 1));
    JLabel lengthLabel = new JLabel("Dash Length");

    // Variables for drawPanel.
    JPanel drawPanel = new JPanel();
    private Color firstColorChoice = Color.BLACK;
    private Color secondColorChoice = Color.BLACK;
    private int selectedShape = 0; // 0 - line, 1 - oval, 2 - rectangle
    private boolean fillShape = false;
    private boolean useGradientPaint = false;
    private boolean useDashedLine = false;
    private float lineWidth = 1.0f;
    private float dashedLength = 10.0f;

    // add status label
    JLabel status = new JLabel();
    private ArrayList<MyShapes> shapes = new ArrayList<>();
  
    // Constructor for DrawingApplicationFrame
    public DrawingApplicationFrame()
    {
        panelOne.setBackground(Color.CYAN);
        panelTwo.setBackground(Color.CYAN);
        
        // add widgets to panels
        firstColor.add(first);
        secondColor.add(second);
        undo.add(undoLabel);
        clear.add(clearLabel);
        width.add(widthLabel);
        length.add(lengthLabel);
        
        // firstLine widgets
        panelOne.add(shape);
        panelOne.add(shapeList);
        panelOne.add(firstColor);
        panelOne.add(secondColor);
        panelOne.add(undo);
        panelOne.add(clear);

        // secondLine widgets
        panelTwo.add(options);
        panelTwo.add(filled);
        panelTwo.add(useGradient);
        panelTwo.add(dashed);
        panelTwo.add(width);
        panelTwo.add(length);

        // add top panel of two panels
        topPanel.setLayout(new GridLayout(2,1));
        topPanel.add(panelOne);
        topPanel.add(panelTwo);

        // add topPanel to North, drawPanel to Center, and statusLabel to South
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);
        drawPanel = new DrawPanel();
        getContentPane().add(drawPanel, BorderLayout.CENTER);
        
        //add listeners and event handlers
        firstColor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Choose First Color", firstColorChoice);
                if (newColor != null){
                    firstColorChoice = newColor;
                }
            }
        });
        
        secondColor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Color newColor = JColorChooser.showDialog(null, "Choose Second Color", secondColorChoice);
                if (newColor != null){
                    secondColorChoice = newColor;
                }
            }
        });
        
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(!shapes.isEmpty()){
                    shapes.remove(shapes.size()-1);
                    repaint();
                }
            }
        });
        
        clear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                while(!shapes.isEmpty()){
                    shapes.remove(shapes.size()-1);
                }
                repaint();
            }
        });
        
        filled.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                fillShape = filled.isSelected();
            }
        });
        
        useGradient.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                useGradientPaint = useGradient.isSelected();
            }
        });
        
        dashed.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                useDashedLine = dashed.isSelected();
            }
        });
        
        width.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                lineWidth = (float)((int) width.getValue());
            }
        });
        
        length.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                dashedLength = (float)((int) length.getValue());
            }
        });
        
    }
    // Create event handlers, if needed

    // Create a private inner class for the DrawPanel.
    private class DrawPanel extends JPanel
    {
        private MouseHandler mouseHandler;
        
        public DrawPanel()
        {
            this.mouseHandler = new MouseHandler();
            super.addMouseMotionListener(this.mouseHandler);
            super.addMouseListener(this.mouseHandler);
            
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            //loop through and draw each shape in the shapes arraylist
            for(MyShapes shape : shapes){
                shape.draw(g2d);
            }
           
        }


        private class MouseHandler extends MouseAdapter implements MouseMotionListener
        {
            private Paint paint;
            private Stroke stroke;
            private boolean filled;
            private String currentShape;

            @Override
            public void mousePressed(MouseEvent event)
            {
                Point startPoint = event.getPoint();
                Point endPoint = event.getPoint();
                currentShape = shapeList.getSelectedItem().toString();
                Color chosenColorOne = firstColorChoice;
                Color chosenColorTwo = secondColorChoice;
                boolean useGradient = useGradientPaint;
                if(useGradient){
                    paint = new GradientPaint(0,0,chosenColorOne,50,50,chosenColorTwo, true);
                }
                else{
                    paint = chosenColorOne;
                }
                if(useDashedLine){
                    dashedLength = dashedLength;
                    float[] dashLengthArray = {dashedLength};
                    stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashLengthArray, 0);
                }
                else{
                    stroke = new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
                }
                filled = fillShape;
                if(currentShape.equals("Rectangle")){
                    MyRectangle rectangle = new MyRectangle(startPoint, endPoint, paint, stroke, filled);
                    shapes.add(rectangle);
                }
                else if (currentShape.equals("Oval")){
                    MyOval oval = new MyOval(startPoint, endPoint, paint, stroke, filled);
                    shapes.add(oval);
                }
                else{
                    MyLine line = new MyLine(startPoint, endPoint, paint, stroke);
                    shapes.add(line);
                }
                status.setText("Pressed and drawing: " + currentShape);
                repaint();
            }

            public void mouseReleased(MouseEvent event)
            {
                shapes.get(shapes.size()-1).setEndPoint(event.getPoint());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent event)
            {
                shapes.get(shapes.size()-1).setEndPoint(event.getPoint());
                repaint();
                status.setText(String.format("(%d,%d)",event.getX(), event.getY()));
            }

            @Override
            public void mouseMoved(MouseEvent event)
            {
                status.setText(String.format("(%d,%d)",event.getX(), event.getY()));
            }
        }

    }
}
