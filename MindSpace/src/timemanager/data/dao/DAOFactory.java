package timemanager.data.dao;

public class DAOFactory {
    private Data data;

    public DAOFactory() {
        this.data = new DataImpl();
    }

    public Data getData() {
        return  this.data;
    }
}
