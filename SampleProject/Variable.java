public class Variable {
    String label;
    String type;
    String name;
    Boolean boolValue;
    int intValue;

    public Variable(String a,String b,String c,String d){
        this.label=a;
        this.type=b;
        this.name=c;
        if(type.equals("int")) {
            intValue=0;
            int temp=1;
            for(int j=0;j<=d.length()-2;j++)temp*=10;
            for(int i=0;i<=d.length()-1;i++){
                intValue+=(temp*(d.charAt(i)-48));
                temp/=10;
            }
        }
        else if(type.equals("bool"))this.boolValue=true;
        else this.boolValue=false;
    }


}
