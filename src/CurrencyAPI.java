package de.zm4xi.currencyapi;

import de.zm4xi.currencyapi.database.MySQL;
import de.zm4xi.currencyapi.object.files.DatabaseFile;
import lombok.Getter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.UUID;

public class CurrencyAPI {

    @Getter
    protected MySQL mySQL;

    @Getter
    protected DatabaseFile databaseFile;

    public CurrencyAPI() {
        databaseFile = new DatabaseFile();
        mySQL = new MySQL(databaseFile.getString("host"), databaseFile.getString("port"), databaseFile.getString("database"), databaseFile.getString("username"), databaseFile.getString("password"));

    }

    /**
     * Remove a {@link Number} from the balance bound to the given {@link UUID}
     *
     * @param uuid used to identify the linked balance
     * @param amount
     */
    public void withdrawBalance(UUID uuid, double amount) {
        try {
            getMySQL().update("UPDATE currency SET balance = balance - ? WHERE uuid = ?", amount, uuid.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a {@link Number} to the balance bound to the given {@link UUID}
     *
     * @param uuid used to identify the linked balance
     * @param amount
     */
    public void depositBalance(UUID uuid, double amount) {
        try {
            getMySQL().update("UPDATE currency SET balance = balance + ? WHERE uuid = ?", amount, uuid.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isCovered(UUID uuid, double amount) {
        return (double) getBalance(uuid) >= amount;
    }

    public boolean isCovered(UUID uuid, int amount) {
        return (double) getBalance(uuid) >= amount;
    }

    public boolean isCovered(UUID uuid, float amount) {
        return (double) getBalance(uuid) >= amount;
    }

    public boolean isCovered(UUID uuid, long amount) {
        return (double) getBalance(uuid) >= amount;
    }

    public boolean isCovered(UUID uuid, byte amount) {
        return (double) getBalance(uuid) >= amount;
    }

    /**
     *  Get the current balance identified by a {@link UUID}
     *
     * @param uuid used to identify the linked balance
     * @return a {@link Number} casted to the given {@link Class<? extends Number>} datatype
     */
    public double getBalance(UUID uuid) {
        ResultSet rs = null;
        try {
            rs = getMySQL().query("SELECT balance FROM currency WHERE uuid = ?", uuid.toString());
            if(rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get a {@link LinkedHashMap<UUID, Number>} with the top balances in the database
     *
     * @param limit to limitate the the size
     * @return a {@link LinkedHashMap<UUID, Number>} with the top balances of the database
     */
    public LinkedHashMap<UUID, Number> getTop(int limit) {
        LinkedHashMap<UUID, Number> map = new LinkedHashMap<>();
        try {
            ResultSet rs = getMySQL().query("SELECT DISTINCT * FROM currency ORDER BY balance DESC LIMIT " + limit);
            while (rs.next()) {
                map.put(UUID.fromString(rs.getString("uuid")), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}