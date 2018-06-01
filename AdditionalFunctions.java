import java.io.Serializable;
import java.util.ArrayList;

public class AdditionalFunctions implements Serializable {


    public static int[][] getConnections(Matrix inputMatrix) {
        int irow = 0;
        int icol = 0;
        int iproject = 0;
        int mSizeRow = inputMatrix.nrow();
        int mSizeCol = inputMatrix.ncol();
        int[][] connectionsMatrix = new int[mSizeRow][mSizeCol];
        double factor = 3.0;

        for (ArrayList<Double> row : inputMatrix.getData()) {
            icol = 0;
            iproject = irow;
            for (Double element : row) {
                if (element >= factor) {
                    if (icol != iproject) {
                        connectionsMatrix[irow][icol] = icol;
                    } else {
                        connectionsMatrix[irow][icol] = -1;
                    }
                } else {
                    connectionsMatrix[irow][icol] = -1;
                }
                icol++;
            }
            irow++;
        }
        return connectionsMatrix;
    }


    public static ArrayList<Integer> getStringForRow(int[][] connectionsMatrix, int project) {
        int numOfProjects = connectionsMatrix.length;
        ArrayList<Integer> connections = new ArrayList<>();
        ArrayList<Integer> searched = new ArrayList<>();

        getConnections(connectionsMatrix, project, connections, searched);

        return connections;
    }

    private static void getConnections(int[][] connectionsMatrix, int project, ArrayList<Integer> connections, ArrayList<Integer> searched) {
        for (int connection : connectionsMatrix[project]) {
            if (searched.indexOf(connection) ==-1 && connection != -1) {
                searched.add(connection);
                addProjectToConnections(connections, connection);
                getConnections(connectionsMatrix, connection, connections, searched);
            }
        }
    }

    private static void addProjectToConnections(ArrayList<Integer> connections, int project) {
        if (connections.indexOf(project) == -1) {
            connections.add(project);
        }
    }


}
