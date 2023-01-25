public class Receipt {
private String id;
    private String date;
    private String clientID;
    private String total;
    private String products;

    public Receipt(String id, String date, String clientID, String total, String products) {
        this.id = id;
        this.clientID = clientID;
        this.date = date;
        this.total = total;
        this.products = products;
    }

    public Object[] receiptObject(){
        return new Object[]{id, date, clientID, total, products};
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void updateReceipt(String id, String date, String clientID, String total, String products){
        setId(id);
        setDate(date);
        setClientID(clientID);
        setTotal(total);
        setProducts(products);
    }


}
