package com.main.axival.card.MapPlay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MoveAea {
    private Vector2[] area;
    private Vector2[] ways;
    private Vector3[] ranges;
    private Board board;

    public MoveAea(Board board) {
    }

    public Vector2[] getArea(int x, int y, int n, Board board) {
        int idx = 0;
        int idx_r = 0;

        for(int i = y - n; i < y - n + 2 * n + 1; ++i) {
            int start;
            int stop;
            if (y % 2 == 1) {
                start = x - n + (int)Math.floor((double)(Math.abs(y - i) / 2));
                stop = start + 2 * n - Math.abs(y - i) + 1;
            } else {
                start = x - n + (int)Math.ceil((double)(Math.abs(y - i) / 2));
                stop = start + 2 * n - Math.abs(y - i) + 1;
            }

            this.ranges[idx_r].set((float)start, (float)i, (float)stop);

            for(int j = start; j < stop; ++j) {
                if (!board.map[i][j].isObstacle() && i >= 0 && j >= 0) {
                    this.area[idx].set((float)i, (float)j);
                    ++idx;
                }
            }
        }

        return this.area;
    }

    public Vector2[] getWays(int x, int y) {
        if (y % 2 == 0) {
            this.ways[0] = new Vector2((float)(x + 1), (float)y);
            this.ways[1] = new Vector2((float)(x + 1), (float)(y + 1));
            this.ways[2] = new Vector2((float)x, (float)(y + 1));
            this.ways[3] = new Vector2((float)(x - 1), (float)y);
            this.ways[4] = new Vector2((float)x, (float)(y - 1));
            this.ways[5] = new Vector2((float)(x + 1), (float)(y - 1));
        } else {
            this.ways[0] = new Vector2((float)(x + 1), (float)y);
            this.ways[1] = new Vector2((float)x, (float)(y + 1));
            this.ways[2] = new Vector2((float)(x - 1), (float)(y + 1));
            this.ways[3] = new Vector2((float)(x - 1), (float)y);
            this.ways[4] = new Vector2((float)(x - 1), (float)(y - 1));
            this.ways[5] = new Vector2((float)x, (float)(y - 1));
        }

        return this.ways;
    }

    public Vector3[] getRanges() {
        return this.ranges;
    }
}
