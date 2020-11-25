package aplikacja.au.KalkulatorAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity
{

    TextView operation; //Wyswietlanie wybraniej liczb, działań
    TextView resultss;  //Wyświetlanie wyniku działań

    String workings = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
        this.setTitle("KalkulatorDS"); //Wyświetlanie napisu na górze kalkulatora
    }

    private void initTextViews()
    {
        operation = (TextView)findViewById(R.id.workingsTextView); //zadeklarowanie ciągu
        resultss = (TextView)findViewById(R.id.resultTextView);
    }

    private void setWorkings(String givenValue)   //Wywoływanie liczby poprzez naciśniecie przycisku w układzie aplikacji wyniki i o naszych działań
    {
        workings = workings + givenValue;
        operation.setText(workings);
    }


    public void equalsOnClick(View view)
    {
        Double result = null;         //Do kalkulatora została zaimplementowana bibioletka do działań matematrycznych ,,Rhino''
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();  //Komunikat o błędzie
        }

        if(result != null)
            resultss.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf() // Potęgowanie liczb
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++)
        {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< workings.length(); i++)
        {
            if(Numeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(Numeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean Numeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.') //jeżeli liczba jest w zakresie 0-9 return true a w przeciwnym przypadku zwróć false
            return true;

        return false;
    }


    public void clearOnClick(View view) //,Czyszczenie'' wyświetlanych liczb i wyników
    {
        operation.setText("");
        workings = "";
        resultss.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;  //Implementacja nawiasów

    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
            {
                setWorkings(")");
                leftBracket = true;
            }
    }

    public void powerOfOnClick(View view)
    {
        setWorkings("^");
    }

    public void divisionOnClick(View view)
    {
        setWorkings("/");
    }

    public void sevenOnClick(View view)
    {
        setWorkings("7");
    }

    public void eightOnClick(View view)
    {
        setWorkings("8");
    }

    public void nineOnClick(View view)
    {
        setWorkings("9");
    }

    public void timesOnClick(View view)
    {
        setWorkings("*");
    }

    public void fourOnClick(View view)
    {
        setWorkings("4");
    }

    public void fiveOnClick(View view)
    {
        setWorkings("5");
    }                  //Wstawianie liczb oraz działań po przez przyciski

    public void sixOnClick(View view)
    {
        setWorkings("6");
    }

    public void minusOnClick(View view)
    {
        setWorkings("-");
    }

    public void oneOnClick(View view)
    {
        setWorkings("1");
    }

    public void twoOnClick(View view)
    {
        setWorkings("2");
    }

    public void threeOnClick(View view)
    {
        setWorkings("3");
    }

    public void plusOnClick(View view)
    {
        setWorkings("+");
    }

    public void decimalOnClick(View view)
    {
        setWorkings(".");
    }

    public void zeroOnClick(View view)
    {
        setWorkings("0");
    }

}