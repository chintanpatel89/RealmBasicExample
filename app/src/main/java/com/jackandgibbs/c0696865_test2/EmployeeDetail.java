package com.jackandgibbs.c0696865_test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;

public class EmployeeDetail extends AppCompatActivity
{
    TextView txtEmployeeID, txtEmployeeName, txtSalary, txtBirthdate, txtCPPEmployee, txtEIEmployee, txtFTax, txtNetIncome;

    String EmployeeID;

    Double Salary = 0.00, CPP = 5.82, EI = 1.63, FTax = 15.00, finalCPP = 0.00, finalEI = 0.00, finalFTax = 0.00, finalSalary = 0.00;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        Realm.init(this);
        realm=Realm.getDefaultInstance();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmployeeID = (TextView) findViewById(R.id.txtEmployeeID);
        txtEmployeeName = (TextView) findViewById(R.id.txtEmployeeName);
        txtBirthdate = (TextView) findViewById(R.id.txtBirthDate);
        txtSalary = (TextView) findViewById(R.id.txtSalary);

        txtCPPEmployee = (TextView) findViewById(R.id.txtCPPEmployee);
        txtEIEmployee = (TextView) findViewById(R.id.txtEIEmployee);
        txtFTax = (TextView) findViewById(R.id.txtFTax);
        txtNetIncome = (TextView) findViewById(R.id.txtNetIncome);

        EmployeeID = getIntent().getExtras().getString("EID");

        EmployeeData myEmployee = realm.where(EmployeeData.class).equalTo("employeeID", EmployeeID).findFirst();

        txtEmployeeID.setText(EmployeeID);
        txtEmployeeName.setText(myEmployee.employeeName);
        getSupportActionBar().setTitle(myEmployee.employeeName);
        txtBirthdate.setText(myEmployee.employeeBirthDate);
        txtSalary.setText(myEmployee.employeeSalary);

        Salary = Double.parseDouble(myEmployee.employeeSalary);

        finalCPP = (Salary * CPP)/100;
        finalEI = (Salary * EI)/100;
        finalFTax = (Salary * FTax)/100;

        finalSalary = Salary - finalCPP - finalEI - finalFTax;

        txtCPPEmployee.setText(finalCPP+"");
        txtEIEmployee.setText(finalEI+"");
        txtFTax.setText(finalFTax+"");
        txtNetIncome.setText(finalSalary+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
