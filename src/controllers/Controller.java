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

    @FXML
    private Button bb_0;

    @FXML
    private Button bb_1;

    @FXML
    private Button bb_2;

    @FXML
    private Button bb_3;

    @FXML
    private Button bb_4;

    @FXML
    private Button bb_5;

    @FXML
    private Button bb_6;

    @FXML
    private Button bb_7;

    @FXML
    private Button bb_8;

    @FXML
    private Button bb_9;

    @FXML
    private Button bb_double;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void onAction(ActionEvent e) {
        while (true) {
            if (e.getSource() == bb_0) {
                add("0");
                break;
            } else if (e.getSource() == bb_1) {
                add("1");
                break;
            } else if (e.getSource() == bb_2) {
                add("2");
                break;
            } else if (e.getSource() == bb_3) {
                add("3");
                break;
            } else if (e.getSource() == bb_4) {
                add("4");
                break;
            } else if (e.getSource() == bb_5) {
                add("5");
                break;
            } else if (e.getSource() == bb_6) {
                add("6");
                break;
            } else if (e.getSource() == bb_7) {
                add("7");
                break;
            } else if (e.getSource() == bb_8) {
                add("8");
                break;
            } else if (e.getSource() == bb_9) {
                add("9");
                break;
            } else if (e.getSource() == bb_double) {
                add(".");
                break;
            }
        }
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

