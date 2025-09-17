package Calculator;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyCalc extends JFrame {
	private JButton[] buttons;
	
	String[] buttonText = {"%","CE","<","/"
					,"7","8","9","*"
					,"4","5","6","-"
					,"1","2","3","+"
					,"+/-","0",".","="};
	
	private JPanel northPanel;
	
	private JPanel centerPanel;
	
	private JTextField textField;
	
	private JPanel getNorthLayout() {
		if (northPanel == null) {
			northPanel = new JPanel();
			northPanel.setLayout(new BorderLayout());
			textField = new JTextField("0");
			textField.setHorizontalAlignment(JTextField.RIGHT);
			textField.setFont(new Font("Cooper",Font.PLAIN,70));
			northPanel.add(textField);
		}
		return northPanel;
	}
	
	private JPanel getCenterLayout() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new GridLayout(5,4,2,2));
			buttons = new JButton[20];
			
			for(int i = 0 ; i < 20 ; i++)
			{
				buttons[i] = new JButton(buttonText[i]);
				buttons[i].setFont(new Font("Cooper",Font.PLAIN, 25));
				buttons[i].addActionListener(new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						String s = textField.getText();
						String l = b.getText();
						
						if (b.getText().charAt(0) == '<') {
							textField.setText(textField.getText().substring(0,textField.getText().length() - 1));
							if (textField.getText().length() == 0)
								textField.setText("0");
						}
						else if (l.equals("CE")) {
							textField.setText("0");
						}
						
						else if (l.equals("%")) {
							textField.setText(s + "%");
						}
						else if (l.equals("=")) {
							try { 
								String exp = textField.getText();
								double result = resultPostfix(exp); 
								
								if (result == (long) result) { //ถ้า 5.0 -> 5 เอาทศนิยมศูนย์ออก
						            textField.setText(String.valueOf((long) result));
						        } else 
						            textField.setText(String.valueOf(result));
								
							} catch (Exception ex) { 
								textField.setText("Error"); 
							} 
						}
						else if (l.equals("+/-")) {
							try {
                                double val = Double.parseDouble(s);
                                val = -val;
                                textField.setText(String.valueOf(val));
                            } catch (Exception ex) {
                                textField.setText("0");
                            }
						}
						else if (l.matches("[0-9]")) {
				            if (s.equals("0")) {
				                textField.setText(l);
				            } else {
				                textField.setText(s + l);
				            }
				        } else {
				            textField.setText(s + l);
				        }
					}
				});
				centerPanel.add(buttons[i]);
			}
		}
		return centerPanel;
	}
	
	private String toPostfix(String exp) {
		StringBuilder output = new StringBuilder(); //ใช้บิ้วเดอร์เพราะต่อง่าย เน้นต่อ
        Stack<String> stack = new Stack<>();
        StringTokenizer tokens = new StringTokenizer(exp, "+-*/", true); //ตัดเป็นโทเค่นด้วย "+-*/" true คือ เก็บตัวที่ใช้ตัดเป็นโทเค่นด้วย (ไม่ทิ้ง)
		
        while (tokens.hasMoreTokens()) {
        	String token = tokens.nextToken().trim(); //ไปโทเค่นตัวถัดไป.ตัดหน้าหลัง
        	char c = token.charAt(0);
        	if (Character.isDigit(c) || token.contains(".")) {
        		if (token.endsWith("%")) {
        			String numberPart = token.substring(0, token.length() - 1);
        			double val = Double.parseDouble(numberPart) / 100.0;
        			output.append(val).append(" "); //คิดเป็นเลขแล้วในสตริงเลย พอกด = จะขึ้นค่า
        		} 
        		else {
        			output.append(token).append(" ");
        		}	
        	}
        	else {
        		while (!stack.isEmpty() && precedence(stack.peek().charAt(0)) >= precedence(token.charAt(0))) {
        			output.append(stack.pop()).append(" "); 
        			}
        		stack.push(token); 
        	}
        	
        }
        while (!stack.isEmpty()) { 
        	output.append(stack.pop()).append(" ");
        }
        return output.toString();
		
	}
	private double resultPostfix(String exp) {
		String postfix = toPostfix(exp);
		Stack<Double> stack = new Stack<>();
		StringTokenizer tokens = new StringTokenizer(postfix);
		
		while (tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			if (token.matches("-?\\d+(\\.\\d+)?")) // "-?" มี-หรือไม่มีก็ได้ (? = มีหรือไม่ก็ได้) "\\d+" digit(0-9) 1 ตัว ++ "(\\.\\d+)?" //มีจุดหรือมากกว่า1ก็ได้
			{
				stack.push(Double.parseDouble(token));
			} else { 
				double b = stack.pop();
				double a = stack.pop();
				switch (token) { 
				case "+": stack.push(a + b); break; 
				case "-": stack.push(a - b); break; 
				case "*": stack.push(a * b); break; 
				case "/": stack.push(a / b); break;
				}
			}
		}
		return stack.pop();
	}
	
	private int precedence(char op) //ลำดับความสำคัญ
	{
		if (op == '-' || op == '+') {
			return 1;
		} else if (op == '*' || op == '/' || op == '%') {
			return 2;
		}
		return 0;
	}
	
	public MyCalc(String title) {
		super(title);

		add(getNorthLayout(),BorderLayout.NORTH);
		add(getCenterLayout(),BorderLayout.CENTER);
		setSize(350,500);
		setVisible(true);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
        new MyCalc("My Calculator");
    }
}
