import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

public class main implements Serializable {

    public static int[][] connectionsMatrix;


    public static void main(String[] args) throws IOException, ClassNotFoundException {

       String inputName = JOptionPane.showInputDialog("Podaj nazwę pliku csv zawierającą macierz danych:",
                "FileName.csv");
        String threshold = JOptionPane.showInputDialog("Podaj graniczną wartość wspólnych projektów.",
                "liczba całkowita dodatnia");



        CsvFileParser inputFile = new CsvFileParser(inputName);
        System.out.println(inputFile.readString());




        Matrix X = inputFile.convertToMatrix();

        if (X.getData() != null){

            X.display();

            Matrix Xt = X.transpose();
            Matrix XtX = Xt.dotProduct(X);
            X.display();
            Xt.display();
            XtX.display();

            System.out.println(XtX.getClass());
            System.out.println(XtX.ncol());
            System.out.println(XtX.nrow());

            connectionsMatrix = AdditionalFunctions.getConnections(XtX);

            //System.out.println("stworzyłem macierz powiązań:");

            int numOfProjects = connectionsMatrix.length;
            //String[] projectNames = inputNames.readString().split("\n");


            System.out.println("Powiązane projekty:");

            for (int i = 0; i < numOfProjects; i++) {
                ArrayList<Integer> connections = AdditionalFunctions.getStringForRow(connectionsMatrix, i);
                System.out.print((i + 1) + " : ");
                for (Integer connection : connections) {
                    System.out.print(String.format("SDG%d,", connection + 1));
                }
             System.out.print("\n");
            }
        }
//
//
//        int i = 0;
//        int j = 0;
//
//        for (i = 0; i<17 ; i++){
//            System.out.println("projekt " + i);
//            for( j = 0; j <17; j++){
//                System.out.print(connectionsMatrix[i][j] + " , ");
//            }
//            System.out.println("\n");
//        }

//


    }


}
