package br.com.puti.core.integrations.http.body.in;

import br.com.puti.core.component.database.mysql.DataBase;
import br.com.puti.core.integrations.http.connection.in.CoreConnection;

public interface Body {

    DataBase getBody();

    String getToken();

    String getName();

    CoreConnection openConnection();
}
