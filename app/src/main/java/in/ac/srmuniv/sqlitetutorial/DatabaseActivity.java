package in.ac.srmuniv.sqlitetutorial;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class DatabaseActivity extends ListActivity {
    private SRMDataSource datasource;
    EditText commentEdit ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        datasource = new SRMDataSource(this);
        datasource.open();
        commentEdit = (EditText)findViewById(R.id.txt1);

        List<SRMQuerys> values = datasource.getAllSRMQuerys();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<SRMQuerys> adapter = new ArrayAdapter<SRMQuerys>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    // Will be called via the onClick attribute
    // of the buttons in main.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<SRMQuerys> adapter = (ArrayAdapter<SRMQuerys>) getListAdapter();
        SRMQuerys SRMQuerys = null;
        // Save the new SRMQuerys to the database
        SRMQuerys = datasource.createSRMQuerys(commentEdit.getText().toString());
        adapter.add(SRMQuerys);
        adapter.notifyDataSetChanged();
        commentEdit.setText("");

    }
    protected void onListItemClick(ListView l, View v, int position, long id) {

        ArrayAdapter<SRMQuerys> adapter = (ArrayAdapter<SRMQuerys>) getListAdapter();
        SRMQuerys SRMQuerys = null;
        SRMQuerys = (SRMQuerys) getListAdapter().getItem(position);
        datasource.deleteSRMQuerys(SRMQuerys);
        adapter.remove(SRMQuerys);



        Toast.makeText(getApplicationContext()," YOU DELETED ONE ITEM on "+"'"+id+"'"+" POSITION",
                Toast.LENGTH_LONG).show();

    }
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
   