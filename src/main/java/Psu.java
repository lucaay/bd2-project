public class Psu {
    private String name;
    private String componentType;
    private boolean modularity;
    private String power;


    public Psu(String name, String componentType, boolean modularity,  String power) {
        this.name = name;
        this.componentType = componentType;
        this.modularity = modularity;
        this.power = power;
    }

    public Object[] psuObject(){
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

    public boolean isModularity() {
        return modularity;
    }

    public void setModularity(boolean modularity) {
        this.modularity = modularity;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
