package com.main.axival.card.MapPlay;

import com.badlogic.gdx.math.Vector2;
import com.main.axival.card.MapPlay.Node;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Board {
    private final static int[][] detail = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    //0 is no obstacle 1 is a obstacle 2 is a Hero
//    private final static int[][] detail =  {
//            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
//            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
//    };

    public Node[][] map;
    private LinkedList<Vector2> list, path, area, area1, area2, ways, temp, HeroCoordinates;
    ;
    private Node n;

    public Board(MapScreen screen) {
        int corX = -12, corY = 619;
        map = new Node[13][24];
        for (int y = 0; y < 13; y++) {
            corY -= 40;
            if (y % 2 == 0) {
                corX = -12;
            } else {
                corX = -40;
            }
            for (int x = 0; x < 24; x++) {
                corX += 51;
                map[y][x] = new Node(x, y, corX, corY, detail[y][x]);
            }
        }
        HeroCoordinates = new LinkedList<Vector2>();
        HeroCoordinates.add(new Vector2(0, 0));
        HeroCoordinates.add(new Vector2(23, 0));
        HeroCoordinates.add(new Vector2(0, 12));
        HeroCoordinates.add(new Vector2(23, 12));
    }

    public List<Vector2> getPath(Vector2 source, Vector2 destination) {
        Vector2 temp1;
        int scrX = (int) source.x;
        int scrY = (int) source.y;
        int desX = (int) destination.x;
        int desY = (int) destination.y;
        path = new LinkedList<Vector2>();
        area = new LinkedList<Vector2>();
        list = new LinkedList<Vector2>();
        list.add(new Vector2(scrX, scrY));
        map[scrY][scrX].setVisit(true);
        temp1 = list.pop();
        while (!list.contains(new Vector2(desX, desY)) && !temp1.equals(new Vector2(desX, desY))) {
            list.addAll(this.getWays((int) temp1.x, (int) temp1.y));
            temp1 = list.pop();
        }
        temp1 = new Vector2(desX, desY);
        while (!temp1.equals(new Vector2(scrX, scrY))) {
            path.addFirst(temp1);
            temp1 = map[(int) temp1.y][(int) temp1.x].getParent();
        }
        this.resetVisit();
        return path;
    }

    public LinkedList<Vector2> getArea(int x, int y, int n) {
        int idx = 0;
        double start;
        double stop;
        area1 = new LinkedList<Vector2>();
        for (double i = y - n; i < y - n + (2 * n) + 1; i++) {
            if (0 <= i && i <= 12) {
                if (y % 2 == 1) {
                    start = (x - n + Math.floor(Math.abs(y - i) / 2));
                    stop = start + (2 * n) - Math.abs(y - i) + 1;
                } else {
                    start = (x - n + Math.ceil(Math.abs(y - i) / 2));
                    stop = start + (2 * n) - Math.abs(y - i) + 1;
                }
                for (double j = start; j < stop; j++) {
                    if (0 <= j && j <= 23) {
                        if (!map[(int) i][(int) j].isObstacle()) {
                            area1.add(idx, new Vector2((int) j, (int) i));
                            idx++;
                        }
                    }
                }
            }
        }
        return area1;
    }

    public LinkedList<Vector2> getWays(int x, int y) {
        area2 = new LinkedList<Vector2>();
        ways = new LinkedList<Vector2>();
        temp = new LinkedList<Vector2>();
        if (y % 2 == 0) {
            temp.add(new Vector2(x + 1, y)); //Right
            temp.add(new Vector2(x + 1, y + 1)); //Right-Down
            temp.add(new Vector2(x, y + 1)); //Left-Down
            temp.add(new Vector2(x - 1, y)); //Left
            temp.add(new Vector2(x, y - 1)); //Left-Up
            temp.add(new Vector2(x + 1, y - 1)); //Up-Right
        } else {
            temp.add(new Vector2(x + 1, y)); //Right
            temp.add(new Vector2(x, y + 1)); //Right-Down
            temp.add(new Vector2(x - 1, y + 1)); //Left-Down
            temp.add(new Vector2(x - 1, y)); //Left
            temp.add(new Vector2(x - 1, y - 1)); //Left-Up
            temp.add(new Vector2(x, y - 1)); //Right-Up
        }
        area2.addAll(this.getArea(x, y, 1));
        temp.retainAll(area2);
        for (Vector2 node : temp) {
            if (!map[(int) node.y][(int) node.x].isVisit()) {
                ways.add(node);
                map[(int) node.y][(int) node.x].setLevel(map[y][x].level + 1);
                map[(int) node.y][(int) node.x].setVisit(true);
                map[(int) node.y][(int) node.x].setParent(x, y);
            }
        }
        return ways;
    }

    public Set<Vector2> getOverlay(int col, int row, int walk) {
        int check = 0;
        Vector2 temp1;
        Set<Vector2> area = new HashSet<Vector2>();
        list = new LinkedList<Vector2>();
        list.add(new Vector2(col, row));
        map[row][col].setVisit(true);
        temp1 = list.pop();
        while (check == 0) {
            list.addAll(this.getWays((int) temp1.x, (int) temp1.y));
            for (Vector2 vec : list) {
                if (0 < map[(int) vec.y][(int) vec.x].level && map[(int) vec.y][(int) vec.x].level <= walk) {
                    area.add(vec);
                    check = 0;
                } else if (map[(int) vec.y][(int) vec.x].level > walk) {
                    check = 1;
                }
            }
            temp1 = list.pop();
        }
        this.resetLevel();
        this.resetVisit();
        return area;
    }

    public void setObstacle(int x, int y, int n) {
        if (0 <= x && x <= 23 && 0 <= y && y <= 12 && !this.map[x][y].isObstacle()) {
            this.map[x][y].setObstacle(n);
        }
    }

    public void resetVisit() {
        for (int row = 0; row < 13; row++) {
            for (int col = 0; col < 24; col++) {
                map[row][col].setVisit(false);
            }
        }
    }

    public void resetLevel() {
        for (int row = 0; row < 13; row++) {
            for (int col = 0; col < 24; col++) {
                map[row][col].setLevel(0);
            }
        }
    }

    public Vector2 getHeroCoordinates() {
        return HeroCoordinates.pop();
    }
}
