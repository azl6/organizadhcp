import java.util.Scanner;

public class Organiza {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String hosts = "", hostName;
        System.out.println("Cole os hosts na tela: ");

        while(sc.hasNext()){
            hosts += sc.nextLine();

            if(hosts.contains("}")){
                hosts = hosts.replaceAll(" ", "");
                hostName = hosts.substring(4, hosts.indexOf("{"));
                hosts = hosts.replace("}", "");
                hosts = hosts.concat(String.format("option host-name \"%s\";}", hostName));
                hosts = fixSpaces(hosts);
                System.out.println(hosts);
                hosts = "";
            }
        }
    }

    public static String fixSpaces(String str){
        String rest = "", before="", after="";
        //espaços depois do ;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == ';'){
                rest+=str.substring(i+1, str.length());
                str = str.substring(0, i+1);

                str = str.concat(" ");
                str = str.concat(rest);

                rest="";
            }
        }

        //espaço entre o a palavra 'host' e o host em si
        rest = str.substring(4, str.length());
        str = str.substring(0, 4);

        str = str.concat(" ");
        str = str.concat(rest);


        //espaço entre '{'
        int indexKey = str.indexOf("{");
        before = str.substring(0, indexKey);
        after = str.substring(indexKey+1);

        str = "";
        str = str.concat(before + " { ");
        str = str.concat(after);

        int indexWordEthernet = str.indexOf("ethernet");
        before = str.substring(0, indexWordEthernet);
        after = str.substring(indexWordEthernet);

        str = "";
        str = str.concat(before + " ");
        str = str.concat(after);

        before = str.substring(0, indexWordEthernet+9);
        after = str.substring(indexWordEthernet+9);

        str = "";
        str = str.concat(before + " ");
        str = str.concat(after);

        int indexWordFixedAdd = str.indexOf("fixed-address");
        before = str.substring(0, indexWordFixedAdd+13);
        after = str.substring(indexWordFixedAdd+13);

        str = "";
        str = str.concat(before + " ");
        str = str.concat(after);

        return str;
    }
}
