package br.com.puti.core.integrations.http.body;

import br.com.puti.core.Core;
import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.integrations.http.body.in.Body;
import br.com.puti.core.integrations.http.connection.iCoreConnection;
import br.com.puti.core.integrations.http.connection.in.CoreConnection;
import br.com.puti.core.integrations.http.exeception.CoreRequestException;
import br.com.puti.core.services.database.DataBaseServices;

import java.util.UUID;


public class iBody implements Body {

    protected DataBase dataBase;
    protected String token;
    protected String name;
    protected CoreConnection connection;

    public iBody(String connectionName) {
        DataBaseServices services = Core.getManager().getDataBaseLoader().getDataBaseServices();
        if (services == null || services.get(connectionName) == null)
            throw new CoreRequestException("DataBase " + connectionName + " not found!");


        this.dataBase = services.get(connectionName);
        this.setup();
    }

    public iBody(DataBase dataBase) {
        if (dataBase == null)
            throw new CoreRequestException("DataBase not found!");

        if (!dataBase.isConnected())
            dataBase.getConnection();


        this.dataBase = dataBase;
        this.setup();
    }

    private void setup() {
        this.token = UUID.randomUUID().toString();
        this.name = dataBase.getID();
    }


    @Override
    public DataBase getBody() {
        return dataBase;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CoreConnection openConnection() {
        if (connection == null)
            connection = new iCoreConnection(this);
        return connection;
    }
}
