public class Psu {
    private String name;
    private String componentType;
    private String modularity;
    private String power;


    public Psu(String name, String componentType, String modularity,  String power) {
        this.name = name;
        this.componentType = componentType;
        this.modularity = modularity;
        this.power = power;
    }

    public Object[] getObject(){
        return new Object[]{name, componentType, modularity, power};
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String isModularity() {
        return modularity;
    }

    public void setModularity(String modularity) {
        this.modularity = modularity;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void updatePsu(String name, String componentType, String modularity,  String power){
        this.name = name;
        this.componentType = componentType;
        this.modularity = modularity;
        this.power = power;
    }
}
