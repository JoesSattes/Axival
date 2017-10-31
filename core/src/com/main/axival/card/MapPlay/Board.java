package com.main.axival.card.MapPlay;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.LinkedList;


public class Board {
    private static final int[][] detail = new int[][]{{0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    public Node[][] map;
    private LinkedList<Vector2> list;
    private Vector2[] path;
    private Vector2[] area;
    private Vector3[] ranges;
    private MoveAea areaChecker;

    public Board() {
        for(int y = 0; y < 13; ++y) {
            for(int x = 0; x < 24; ++x) {
                this.map[y][x] = new Node(x, y, detail[y][x]);
            }
        }

    }

    public Vector2[] getPath(int scrX, int scrY, int desX, int desY, int walk) {
        this.list = new LinkedList();
        this.area = this.areaChecker.getArea(scrX, scrY, walk, this);
        this.ranges = this.areaChecker.getRanges();
        this.list.add(new Vector2((float)scrX, (float)scrY));

        Vector2 temp;
        do {
            temp = (Vector2)this.list.pop();
            this.list.addAll(Arrays.asList(this.areaChecker.getWays((int)temp.x, (int)temp.y)));
        } while(!temp.equals(new Vector2((float)desX, (float)desY)));

        return this.path;
    }

    public void setObstacle(int x, int y, int n) {
        if (0 <= x && x <= 23 && 0 <= y && y <= 12 && !this.map[x][y].isObstacle()) {
            this.map[x][y].setObstacle(n);
        }

    }
}
