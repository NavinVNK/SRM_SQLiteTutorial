package in.ac.srmuniv.sqlitetutorial;

public class SRMQuerys {
	   
    private long id;
    private String query;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getquery() {
        return query;
    }

    public void setquery(String query) {
        this.query = query;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return query;
    }

}