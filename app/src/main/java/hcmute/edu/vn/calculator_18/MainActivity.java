package hcmute.edu.vn.calculator_18;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
    Button dotButton, clearButton, addButton, subButton, mulButton, divButton, equalButton;
    TextView screenText;

    boolean checkDot = false, checkEqual = false;
    double numberOne = 0, numberTwo = 0, numberResult = 0;
    String screen = "", calculation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        clearButton.setOnClickListener(v -> {
            screenText.setText("");
            screenText.setHint("0");
            checkDot = false;
            checkEqual = false;
            numberOne = 0;
            numberTwo = 0;
            screen = "";
            calculation = "";
        });

        zeroButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("0");
            }
        });
        oneButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("1");
            }
        });
        twoButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("2");
            }
        });
        threeButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("3");
            }
        });
        fourButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("4");
            }
        });
        fiveButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("5");
            }
        });
        sixButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("6");
            }
        });
        sevenButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("7");
            }
        });
        eightButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("8");
            }
        });
        nineButton.setOnClickListener(v -> {
            if (!checkInputLimit()) {
                handleInput("9");
            }
        });
        dotButton.setOnClickListener(v -> {
            if (!checkDot) {
                if (!screenText.getText().toString().isEmpty()) {
                    screen = screenText.getText().toString();
                    screenText.setText(screen.concat("."));
                } else {
                    screenText.setText("0".concat("."));
                }
                checkDot = true;
            }
        });

        addButton.setOnClickListener(v -> {
            screen = screenText.getText().toString();
            if (calculation.isEmpty()) {
                if (!checkEqual) {
                    numberOne = saveNumber(screenText);
                }
                saveCalculation("\u002B", "+");
            } else if (!screen.isEmpty()) {
                numberTwo = saveNumber(screenText);
                if (!checkDivByZero()) {
                    numberOne = handleCalculation(numberOne, numberTwo, calculation);
                    saveCalculation("\u002B", "+");
                }
            } else {
                saveCalculation("\u002B", "+");
            }
            checkDot = false;
            checkEqual = false;
        });
        subButton.setOnClickListener(v -> {
            screen = screenText.getText().toString();
            if (!screen.isEmpty()) {
                if (calculation.isEmpty()) {
                    if (!checkEqual) {
                        numberOne = saveNumber(screenText);
                    }
                    saveCalculation("\u002D", "-");
                } else {
                    numberTwo = saveNumber(screenText);
                    if (!checkDivByZero()) {
                        numberOne = handleCalculation(numberOne, numberTwo, calculation);
                        saveCalculation("\u002D", "-");
                    }
                }
            } else {
                screenText.setText("-");
            }
            checkDot = false;
            checkEqual = false;
        });
        mulButton.setOnClickListener(v -> {
            screen = screenText.getText().toString();
            if (calculation.isEmpty()) {
                if (!checkEqual) {
                    numberOne = saveNumber(screenText);
                }
                saveCalculation("\u00D7", "*");
            } else if (!screen.isEmpty()) {
                numberTwo = saveNumber(screenText);
                if (!checkDivByZero()) {
                    numberOne = handleCalculation(numberOne, numberTwo, calculation);
                    saveCalculation("\u00D7", "*");
                }
            } else {
                saveCalculation("\u00D7", "*");
            }
            checkDot = false;
            checkEqual = false;
        });
        divButton.setOnClickListener(v -> {
            screen = screenText.getText().toString();
            if (calculation.isEmpty()) {
                if (!checkEqual) {
                    numberOne = saveNumber(screenText);
                }
                saveCalculation("\u00F7", "/");
            } else if (!screen.isEmpty()) {
                numberTwo = saveNumber(screenText);
                if (!checkDivByZero()) {
                    numberOne = handleCalculation(numberOne, numberTwo, calculation);
                    saveCalculation("\u00F7", "/");
                }
            } else {
                saveCalculation("\u00F7", "/");
            }
            checkDot = false;
            checkEqual = false;
        });

        equalButton.setOnClickListener(v -> {
            if (!checkEqual) {
                numberTwo = saveNumber(screenText);
                if (!checkDivByZero()) {
                    numberResult = handleCalculation(numberOne, numberTwo, calculation);
                    long numberResultLong = (long) numberResult;
                    String limit = String.valueOf(numberResultLong);
                    if (limit.length() > 17) {
                        Toast.makeText(MainActivity.this, "Chỉ có thể hiện kết quả dưới 18 số", Toast.LENGTH_SHORT).show();
                    } else {
                        numberOne = numberResult;
                        screenText.setText("");
                        if (!(Math.ceil(numberResult) == Math.floor(numberResult))) {
                            screenText.setText(String.valueOf(numberResult));
                            checkDot = true;
                        } else {
                            screenText.setText(limit);
                            checkDot = false;
                        }
                        checkEqual = true;
                        calculation = "";
                    }
                }
            }
        });
    }

    protected void mapping() {
        zeroButton = findViewById(R.id.button_zero);
        oneButton = findViewById(R.id.button_one);
        twoButton = findViewById(R.id.button_two);
        threeButton = findViewById(R.id.button_three);
        fourButton = findViewById(R.id.button_four);
        fiveButton = findViewById(R.id.button_five);
        sixButton = findViewById(R.id.button_six);
        sevenButton = findViewById(R.id.button_seven);
        eightButton = findViewById(R.id.button_eight);
        nineButton = findViewById(R.id.button_nine);
        addButton = findViewById(R.id.button_add);
        subButton = findViewById(R.id.button_sub);
        mulButton = findViewById(R.id.button_mul);
        divButton = findViewById(R.id.button_div);
        dotButton = findViewById(R.id.button_dot);
        clearButton = findViewById(R.id.button_clear);
        equalButton = findViewById(R.id.button_equal);
        screenText = findViewById(R.id.text_screen);
    }

    public void handleInput(String number) {
        if (!screenText.getText().toString().equals("0")) {
            screen = screenText.getText().toString();
            screenText.setText(screen.concat(number));
        } else {
            screenText.setText(number);
        }
    }

    public boolean checkInputLimit() {
        if (screenText.getText().toString().length() == 15) {
            Toast.makeText(MainActivity.this, "Không thể nhập nhiều hơn 15 ký tự", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public void saveCalculation(String symbol, String calculate) {
        screenText.setText("");
        screenText.setHint(symbol);
        screen = "";
        calculation = calculate;
    }

    public double saveNumber(TextView text) {
        double number;
        String num = text.getText().toString();
        if ((num.isEmpty()) || (num.equals("-"))) {
            number = 0;
        } else {
            if (num.endsWith(".")) {
                num = num + "0";
            }
            number = Double.parseDouble(num + "");
        }
        return number;
    }

    public boolean checkDivByZero() {
        if ((numberTwo == 0) && (calculation.equals("/"))) {
            Toast.makeText(MainActivity.this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    public double handleAdd(double number1, double number2) {
        double number;
        if (number1 == 0) {
            number = number2;
        } else if (number2 == 0) {
            number = number1;
        } else {
            number = number1 + number2;
        }
        return number;
    }

    public double handleSub(double number1, double number2) {
        double number;
        if (number1 == 0) {
            number = number2;
        } else if (number2 == 0) {
            number = number1;
        } else {
            number = number1 - number2;
        }
        return number;
    }

    public double handleMul(double number1, double number2) {
        double number;
        if ((number1 == 0) || (number2 == 0)) {
            number = 0;
        } else {
            number = number1 * number2;
        }
        return number;
    }

    public double handleDiv(double number1, double number2) {
        double number;
        if (number1 == 0) {
            number = 0;
        } else {
            number = number1 / number2;
        }
        return number;
    }

    public double handleCalculation(double number1, double number2, String calculate) {
        double number;
        switch (calculate) {
            case "+":
                number = handleAdd(number1, number2);
                break;
            case "-":
                number = handleSub(number1, number2);
                break;
            case "*":
                number = handleMul(number1, number2);
                break;
            case "/":
                number = handleDiv(number1, number2);
                break;
            default:
                number = 0;
        }
        return number;
    }
}