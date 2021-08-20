package utp.misiontic2022.c2.p66.reto4.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utp.misiontic2022.c2.p66.reto4.modelo.vo.Requerimiento_3;
import utp.misiontic2022.c2.p66.reto4.util.JDBCUtilities;

public class Requerimiento_3Dao {
    public ArrayList<Requerimiento_3> requerimiento3() throws SQLException {
        ArrayList<Requerimiento_3> resultados = new ArrayList<>();

        String queryConstructoras = "SELECT p.Banco_Vinculado , SUM(l.Salario) AS \"Costo de líderes\" " +
                                    "FROM Proyecto p JOIN Lider l ON p.ID_Lider = l.ID_Lider " +
                                    "GROUP BY p.Banco_Vinculado " +
                                    "HAVING \"Costo de líderes\" < 30000000 LIMIT 100";

        JDBCUtilities conexion = new JDBCUtilities();
        ResultSet resultadoConstructoras = conexion.ejecutarConsulta(queryConstructoras);

        while (resultadoConstructoras.next()) {
            String bancoVinculado = resultadoConstructoras.getString("Banco_Vinculado");
            int costoLideres = resultadoConstructoras.getInt("Costo de líderes");
            Requerimiento_3 filaResultado = new Requerimiento_3(bancoVinculado, costoLideres);
            resultados.add(filaResultado);
        }

        return resultados;
    }
}