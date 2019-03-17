package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    private String sign = "";
    private String lv = "";
    private String rv = "";
    public String str = "";
    public static boolean newValue = false;
	public static boolean newSign = true;


    @FXML
    private TextField textField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onAction(ActionEvent e) {
        String id = ((Button)e.getSource()).getId();
        String txt = id.substring(3);
        if(txt.equals("double")) txt = ".";
        add(txt);
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
    		if (newValue) {
    			bb_reset();
			}
			str = textField.getText();
			str = removeCharAt(str, str.length()-1);
			textField.setText(str);
			if (rv != "") {
				rv = removeCharAt(rv, rv.length()-1);
			} else if (sign != "") {
				sign = removeCharAt(rv, sign.length()-1);
				newSign = false;
			} else if (lv != "") {
				lv = removeCharAt(rv, lv.length()-1);
			}
    }

	public static String removeCharAt(String s, int pos) {
		return s.substring(0, pos);
	}

    private void recalc() {
        if(sign != "" && rv != "") {
            calc();
            newValue = false;
            newSign = true;

        }
    }
    public void calc() {
    	if (rv != "") {
			Double dlv = Double.parseDouble(lv);
			Double drv = Double.parseDouble(rv);
			switch (sign) {
				case "*":
                    dlv = dlv * drv;
					break;
				case "/":
                    dlv = dlv / drv;
					break;
				case "+":
                    dlv = dlv + drv;
					break;
				case "-":
                    dlv = dlv - drv;
					break;
				default:
					break;
			}

			if (sign != "") {
				lv = String.valueOf(dlv);
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

