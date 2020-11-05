public class NBody {
    public static double readRadius(String file){
        In in = new In(file);

        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }
    public static Body[] readBodies(String file){
        In in = new In(file);
        int num = in.readInt();
        double secondItemInFile = in.readDouble();
        Body[] allbodys= new Body[num] ;
        for(int i=0;i<num;i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            allbodys[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return allbodys;
    }
    public static void main(String[] args) {
        double T= Double.parseDouble(args[0]);
        double dt= Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        Body[] allbodys = readBodies(filename);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Body b:allbodys){
            b.draw();
        }

        StdDraw.enableDoubleBuffering();

        for(double t=0; t<=T;t+=dt) {
            double[] xForces = new double[allbodys.length];
            double[] yForces = new double[allbodys.length];

            for (int i=0; i<allbodys.length; i++) {
                xForces[i] = allbodys[i].calcNetForceExertedByX(allbodys);
                yForces[i] = allbodys[i].calcNetForceExertedByY(allbodys);
            }

            for (int i=0;i<allbodys.length;i++){
                allbodys[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0,0,"images/starfield.jpg");

            for(Body b:allbodys){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allbodys.length);
        StdOut.printf("%.2e\n", radius);
        for (Body b : allbodys) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    b.xxPos, b.yyPos, b.xxVel,
                    b.yyVel, b.mass, b.imgFileName);

        }

    }
}
