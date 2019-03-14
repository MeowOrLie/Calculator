package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class Controller {
    private String sign = "";
    private String lv = "";
    private String rv = "";
    public String str = "";
    public static boolean newValue = false;
	public static boolean newSign = true;
    public static double result;

    @FXML
    private TextField textField;

    public void bb_0() {
        add("0");
    }

    public void bb_1() {
        add("1");
    }

    public void bb_2() {
        add("2");
    }

    public void bb_3() {
        add("3");
    }

    public void bb_4() {
        add("4");
    }

    public void bb_5() {
        add("5");
    }

    public void bb_6() {
        add("6");
    }

    public void bb_7() {
        add("7");
    }

    public void bb_8() {
        add("8");
    }

    public void bb_9() {
        add("9");
    }

    public void bb_double() {
        add(".");
    }

    private void add(String txt) {
    	if (newValue) {
			bb_reset();
		}
    	newValue = false;
        if (sign == "") {
            lv += txt;
        } else {
            rv += txt;
        }
        newSign = false;
        update();
    }

    public void bb_mul() {
    	if (!newSign) {
			recalc();
			sign = "*";
			update();
		}
    }

    public void bb_div() {
		if (!newSign) {
			recalc();
			sign = "/";
			update();
		}
    }

    public void bb_plus() {
		if (!newSign) {
			recalc();
			sign = "+";
			update();
		}
    }

    public void bb_minus() {
		if (!newSign) {
			recalc();
			sign = "-";
			update();
        }
    }

    public void bb_eql() {
        calc();
        update();
    }
    public void bb_reset(){
        textField.setText("");
        lv = "";
        sign = "";
        rv = "";
		newValue = true;
		newSign = true;
    }

    public void bb_cancel(){
    	while (true) {
    		if (newValue) {
    			bb_reset();
    			break;
			}
			str = textField.getText();
			str = removeCharAt(str, str.length()-1);
			textField.setText(str);
			if (rv != "") {
				rv = removeCharAt(rv, rv.length()-1);
				break;
			} else if (sign != "") {
				sign = removeCharAt(rv, sign.length()-1);
				newSign = false;
				break;
			} else if (lv != "") {
				lv = removeCharAt(rv, lv.length()-1);
				break;
			}
			break;
		}
    }

	public static String removeCharAt(String s, int pos) {
		return s.substring(0, pos);
	}

    private void recalc() {
        if(sign != "" && rv != "") {
            calc();
        }
    }
    public void calc() {
    	if (rv != "") {
			Double dlv = Double.parseDouble(lv);
			Double drv = Double.parseDouble(rv);
			switch (sign) {
				case "*":
					result = dlv * drv;
					break;
				case "/":
					result = dlv / drv;
					break;
				case "+":
					result = dlv + drv;
					break;
				case "-":
					result = dlv - drv;
					break;
				default:
					break;
			}

			if (sign != "") {
				lv = String.valueOf(result);
				sign = "";
				rv = "";
			}
			newValue = true;
			newSign = true;
			update();
		}
    }

    public void update() {
        textField.setText(lv + sign + rv);
    }
}

