package com.alibaba.demo.domain;


public abstract class BaseEntity {
    protected ChangingStatus changingStatus = ChangingStatus.UNCHANGED;

    public ChangingStatus getChangingStatus() {
        return changingStatus;
    }

    public void toUpdate(){
        this.changingStatus =  ChangingStatus.UPDATED;
    }

    public void toDelete(){
        this.changingStatus =  ChangingStatus.DELETED;
    }

    public  void toUnchanged(){
        this.changingStatus =  ChangingStatus.UNCHANGED;
    }

    public void toNew(){
        this.changingStatus =  ChangingStatus.NEW;
    }

}
