public class Receipt {
private int id;
    private int clientID;
    private String date;
    private int total;
    private String products;

    public Receipt(int id, int clientID,String date, int total, String products) {
        this.id = id;
        this.clientID = clientID;
        this.date = date;
        this.total = total;
        this.products = products;
    }

    public Object[] receiptObject(){
        return new Object[]{id,clientID, date, total, products};
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }



}
