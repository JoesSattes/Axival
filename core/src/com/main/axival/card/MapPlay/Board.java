package com.main.axival.card.MapPlay;

import com.badlogic.gdx.math.Vector2;
import com.main.axival.card.MapPlay.Node;


import java.util.LinkedList;
import java.util.List;

public class Board {
    //0 is no obstacle 1 is a obstacle 2 is a Hero
    private final static int[][] detail =  {
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public Node[][] map;
    private LinkedList<Vector2> list, area, path, area1, area2, ways, temp;
    private Node n;

    public Board(MapScreen screen) {
        int corX=-12,corY=619;
        map = new Node[13][24];
        for (int y=0; y < 13; y++) {
            corY -= 40;
            if (y%2 == 0) { corX = -12;}
            else {corX = -40;}
            for (int x=0; x < 24; x++) {
                corX += 51;
                map[y][x] = new Node(x, y, corX, corY, detail[y][x]);
            }
        }
    }

    public List<Vector2> getPath(int scrX, int scrY, int desX, int desY, int walk) {
        Vector2 temp;
        area = new LinkedList<Vector2>();
        path = new LinkedList<Vector2>();
        list = new LinkedList<Vector2>();
        list.add(new Vector2(scrX, scrY));
        map[scrY][scrX].setVisit(true);
        int k = 0;
        do {
            temp = list.pop();
            list.addAll(this.getWays((int)temp.x, (int)temp.y, this));
            if (k == 2) {
                for (Vector2 vec: list) {
                    System.out.println((int)vec.x+","+(int)vec.y);
                }
                System.out.println("Kuy 0/0");
                int f = 0/0;
            }
            k++;
        }while (!temp.equals(new Vector2(desX, desY)));
        return path;
    }

    public LinkedList<Vector2> getArea(int x, int y, int n) {
        System.out.println("getArea x = " + x + " y = " + y + " n = " + n);
        int idx = 0;
        int start;
        int stop;
        area1 = new LinkedList<Vector2>();
        for (int i = y-n; i < y-n+2*n+1; i++) {
            if (0 <= i && i <= 12) {
                if (y%2==1) {
                    start = (x - n + (int)Math.floor(Math.abs(y-i)/2));
                    stop = start + 2*n - Math.abs(y-i)+1;
                }
                else {
                    start = (x - n + (int)Math.ceil(Math.abs(y-i)/2));
                    stop = start + 2*n-Math.abs(y-i)+1;
                }
                for (int j = start; j < stop; j++) {
                    if (0 <= j && j <= 23) {
                        if (!map[i][j].isObstacle() == true && i >= 0 && j >= 0) {
                            area1.add(idx, new Vector2(j, i));
                            idx++;
                        }
                    }
                }
                System.out.println("Range(" +start+","+start+")");
            }
        }
        System.out.println("("+x+","+y+") check area1 before return");
        for (Vector2 vec: area1) {
            System.out.println((int)vec.x+","+(int)vec.y);
        }
        return area1;
    }

    public LinkedList<Vector2> getWays(int x, int y, Board board) {
        area2 = new LinkedList<Vector2>();
        ways = new LinkedList<Vector2>();
        temp = new LinkedList<Vector2>();
        if (y%2 == 0) {
            temp.add(new Vector2(x+1, y));
            temp.add(new Vector2(x+1, y+1));
            temp.add(new Vector2(x, y+1));
            temp.add(new Vector2(x-1, y));
            temp.add(new Vector2(x, y-1));
            temp.add(new Vector2(x+1, y-1));
        }
        else {
            temp.add(new Vector2(x+1, y));
            temp.add(new Vector2(x, y+1));
            temp.add(new Vector2(x-1, y+1));
            temp.add(new Vector2(x-1, y));
            temp.add(new Vector2(x-1, y-1));
            temp.add(new Vector2(x, y-1));
        }
//        System.out.println("Check temp after all 1 step direction");
//        for (Vector2 vec: temp) {
//            System.out.println((int)vec.x+","+(int)vec.y);
//        }
        area2.addAll(this.getArea(x, y, 1));
//        System.out.println("Area for retain" + x + "," + y);
//        for (Vector2 vec: area2) {
//            System.out.println((int)vec.x+","+(int)vec.y);
//        }
        temp.retainAll(area2);
//        System.out.println("After temp.retainAll(area);");
//        for (Vector2 vec: temp) {
//            System.out.println((int)vec.x+","+(int)vec.y);
//        }
//        System.out.println(x+","+y+" around and not visit");
        for (Vector2 node: temp) {
            System.out.println((int)node.x+","+(int)node.y+"isVisit="+board.map[(int)node.y][(int)node.x].isVisit());
            if (!board.map[(int)node.y][(int)node.x].isVisit()) {
                System.out.println((int)node.x+","+(int)node.y);
                ways.add(node);
                board.map[(int)node.y][(int)node.x].setVisit(true);
                board.map[(int)node.y][(int)node.x].setParent(x, y);
            }
        }
        System.out.println("Way before return of "+x+","+y);
        for (Vector2 vec: ways) {
            System.out.println((int)vec.x+","+(int)vec.y);
        }
        System.out.println("Ways will return");
        return  ways;
    }

    public void setObstacle(int x, int y, int n) {
        if (0 <= x && x <= 23 && 0 <= y && y <= 12 && !this.map[x][y].isObstacle()) {
            this.map[x][y].setObstacle(n);
        }
    }
}
