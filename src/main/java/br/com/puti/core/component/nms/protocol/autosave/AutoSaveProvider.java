package br.com.puti.core.component.nms.protocol.autosave;

import br.com.puti.core.component.nms.protocol.autosave.partial.SyncSave;
import br.com.puti.core.loader.CoreLoader;

import java.util.ArrayList;
import java.util.List;

public class AutoSaveProvider {

    protected List<SyncSave> sncySaves = new ArrayList<SyncSave>();

    protected static AutoSaveProvider provider;
    protected CoreLoader loader;

    public AutoSaveProvider(CoreLoader loader) {
        if (loader.getAutoSaveProvider() != null)
            throw new NullPointerException("Classe nao pode ser instancia.(?)");
        else
            this.provider = this;

        this.loadAll();
    }


    public void register(SyncSave arg0) {
        this.sncySaves.add(arg0);
    }

    public void saveAll() {
        this.sncySaves.forEach(asSave -> {
            asSave.save();
        });
    }


    public void loadAll() {
        this.sncySaves.forEach(asSave -> {
            asSave.load();
        });
    }

    public static AutoSaveProvider getAutoSave() {
        return provider;
    }
}

