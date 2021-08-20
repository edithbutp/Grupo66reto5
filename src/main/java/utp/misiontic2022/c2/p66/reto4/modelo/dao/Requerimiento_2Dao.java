package utp.misiontic2022.c2.p66.reto4.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utp.misiontic2022.c2.p66.reto4.modelo.vo.Requerimiento_2;
import utp.misiontic2022.c2.p66.reto4.util.JDBCUtilities;

public class Requerimiento_2Dao {
    public ArrayList<Requerimiento_2> requerimiento2() throws SQLException {
        ArrayList<Requerimiento_2> resultados = new ArrayList<>();

        String queryProveedores = "SELECT Proveedor, SUM(Cantidad) AS \"Cantidad total\" " +
                                  "FROM Compra GROUP BY Proveedor "+
                                  "ORDER BY \"Cantidad total\" ASC LIMIT 100";

        JDBCUtilities conexion = new JDBCUtilities();
        ResultSet resultadoProveedores = conexion.ejecutarConsulta(queryProveedores);

        while (resultadoProveedores.next()) {
            String proveedor = resultadoProveedores.getString("Proveedor");
            int cantidadTotal = resultadoProveedores.getInt("Cantidad total");
            Requerimiento_2 filaResultado = new Requerimiento_2(proveedor, cantidadTotal);
            resultados.add(filaResultado);
        }

        return resultados;
    }
}