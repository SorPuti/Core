package br.com.puti.core.component.nms.protocol.autosave.partial;

import br.com.puti.core.component.nms.protocol.autosave.AutoSaveProvider;

import java.util.Random;

public abstract class SyncSave implements SyncSaveRegistry {

    private int serialID = 0;

    public SyncSave(){
        AutoSaveProvider.getAutoSave().register(this);
        this.init();
    }

    private void init(){
        this.serialID = new Random().nextInt();
    }

    public int getSerialID() {
        return serialID;
    }
}
