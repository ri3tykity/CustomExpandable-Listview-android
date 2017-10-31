package com.example.naturebackendteam.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private CustomAdapter adapter;
    // Data from server store it in rawData variable
    private JSONObject rawData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = (ExpandableListView) findViewById(R.id.exp_listview);

        try {
            rawData = new JSONObject("{\"status\":\"true\",\"message\":\"Day Wise Timesheet Generated Successfully\",\"DayWiseTimeSheet\":[{\"EmpName\":\"Snyder B\",\"PeriodRange\":\"10-10-2017(10/01/2017 - 10/09/2017)\",\"statusCode\":7,\"WeekDays\":[{\"WeekDate\":\"10/01/2017\",\"TotalHours\":0.00,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Sunday  Oct  1\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Monday  Oct  2\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Tuesday  Oct  3\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Wednesday  Oct  4\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Thursday  Oct  5\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Friday  Oct  6\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Saturday  Oct  7\",\"Hours\":0.00,\"ActiveYN\":false}]},{\"WeekDate\":\"10/02/2017\",\"TotalHours\":40.50,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Monday  Oct  2\",\"Hours\":8.50,\"ActiveYN\":true},{\"DayName\":\"Tuesday  Oct  3\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Wednesday  Oct  4\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Thursday  Oct  5\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Friday  Oct  6\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Saturday  Oct  7\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Sunday  Oct  8\",\"Hours\":0.00,\"ActiveYN\":true}]},{\"WeekDate\":\"10/09/2017\",\"TotalHours\":40.00,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Monday  Oct  9\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Tuesday  Oct 10\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Wednesday  Oct 11\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Thursday  Oct 12\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Friday  Oct 13\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Saturday  Oct 14\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Sunday  Oct 15\",\"Hours\":0.00,\"ActiveYN\":true}]}]},{\"EmpName\":\"Krishna\",\"PeriodRange\":\"(10/01/2017 - 10/09/2017)\",\"statusCode\":7,\"WeekDays\":[{\"WeekDate\":\"10/01/2017\",\"TotalHours\":0.00,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Sunday  Oct  1\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Monday  Oct  2\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Tuesday  Oct  3\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Wednesday  Oct  4\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Thursday  Oct  5\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Friday  Oct  6\",\"Hours\":0.00,\"ActiveYN\":false},{\"DayName\":\"Saturday  Oct  7\",\"Hours\":0.00,\"ActiveYN\":false}]},{\"WeekDate\":\"10/02/2017\",\"TotalHours\":40.50,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Monday  Oct  2\",\"Hours\":8.50,\"ActiveYN\":true},{\"DayName\":\"Tuesday  Oct  3\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Wednesday  Oct  4\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Thursday  Oct  5\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Friday  Oct  6\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Saturday  Oct  7\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Sunday  Oct  8\",\"Hours\":0.00,\"ActiveYN\":true}]},{\"WeekDate\":\"10/09/2017\",\"TotalHours\":40.00,\"EmpNote\":\"\",\"SupNote\":\"\",\"DayStatus\":7,\"DayHrs\":[{\"DayName\":\"Monday  Oct  9\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Tuesday  Oct 10\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Wednesday  Oct 11\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Thursday  Oct 12\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Friday  Oct 13\",\"Hours\":8.00,\"ActiveYN\":true},{\"DayName\":\"Saturday  Oct 14\",\"Hours\":0.00,\"ActiveYN\":true},{\"DayName\":\"Sunday  Oct 15\",\"Hours\":0.00,\"ActiveYN\":true}]}]}]}");

            // customize your data here
            prepareListData();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new CustomAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(adapter);

        expandAll();
    }

    // You may hide then you have to expand each section by clicking it.
    private void expandAll() {
        int count = adapter.getGroupCount();
        for (int i = 0; i < count; i++){
            expandableListView.expandGroup(i);
        }
    }

    private void prepareListData() throws JSONException {
        // Just for reference
        String status = rawData.getString("status");
        String message = rawData.getString("message");
        // Init item storage for parent list and child list
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // Get actual object
        JSONArray resultData = rawData.getJSONArray("DayWiseTimeSheet");
        int length = resultData.length();
        // Loop over data to make it dynamic fill
        for(int i = 0; i<length; i++) {
            // Use custom class to get store data for parent listview - I used ParentListViewData, change it as per your requirement
            JSONObject singleData = rawData.getJSONArray("DayWiseTimeSheet").getJSONObject(i);
            // Extract data for parent view
            String empName = singleData.getString("EmpName");
            String periodRange = singleData.getString("PeriodRange");
            // Store in class object and convert class to json string.
            ParentListViewData parentListView = new ParentListViewData(empName, periodRange);
            String pItemStr = new Gson().toJson(parentListView);
            // Add string to parent list
            listDataHeader.add(pItemStr);
            // Now work for nested child list - may be you find somewhere in same object.
            JSONArray weekDays = singleData.getJSONArray("WeekDays");
            int anotherLength = weekDays.length();
            // Loop through child data
            List<String> childItems = new ArrayList<String>();
            for(int j = 0; j<anotherLength; j++) {
                // Get items as per your requirement
                String weekdate = weekDays.getJSONObject(j).getString("WeekDate");
                String totalHours = weekDays.getJSONObject(j).getString("TotalHours");
                String dayStatus = weekDays.getJSONObject(j).getString("DayStatus");
                // Add to child class - I used this ChildListViewData, you can change member as per your requirement.
                ChildListViewData childListView = new ChildListViewData(weekdate, totalHours, dayStatus);
                // Add it to child list.
                childItems.add(new Gson().toJson(childListView));
            }
            // Put all together
            listDataChild.put(pItemStr, childItems); // Header, Child data
        }
    }
}
