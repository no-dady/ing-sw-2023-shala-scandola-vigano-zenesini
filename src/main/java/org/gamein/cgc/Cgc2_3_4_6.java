package org.gamein.cgc;

import org.gamein.model.Tile;

import java.util.Arrays;

public class Cgc2_3_4_6 implements CommonGoalCardCondition {

    public Cgc2_3_4_6() {

    }

    // 4 vertical strips of 4 tiles of the same type
    @Override
    public boolean ConditionCheck(Tile[][] slots, int numToLook, boolean isEqual, boolean isVert) {
        return isVert ? checkColumns(slots, numToLook, isEqual) : checkRows(slots, numToLook, isEqual);
    }

    private boolean checkColumns(Tile[][] slots, int numToLook, boolean isEqual) {
        int count, checked = 0;

        for(int i = 0; i < slots[0].length; i++) {
            count = (int) Arrays.stream(slots[i]).distinct().count();
            if(isEqual) {
                if (count < slots[0].length - numToLook) checked++;
            } else {
                if(count == numToLook) checked++;
            }
            if (numToLook - checked < slots[0].length - i) {
                return false;
            }
        }
        return true;
    }

    private boolean checkRows(Tile[][] slots, int numToLook, boolean isEqual) {
        int count, checked = 0;

        for(int i = 0; i < slots.length; i++) {
            count = (int) Arrays.stream(slots[i]).distinct().count();
            if(isEqual) {
                if (count < slots.length - numToLook) checked++;
            } else {
                if(count == numToLook) checked++;
            }
            if (numToLook - checked < slots.length - i) {
                return false;
            }
        }

        return true;
    }
}
