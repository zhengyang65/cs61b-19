public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;
    }


    public double calcDistance(Body b){
        double r1;
        r1 = (b.xxPos-this.xxPos)*(b.xxPos-this.xxPos) + (b.yyPos-this.yyPos)*(b.yyPos-this.yyPos);
        r1 =Math.sqrt(r1);
        return r1;
    }
    public double calcForceExertedBy(Body b){
        double r2, fF, gG;
        gG = 6.67e-11;
        r2 = this.calcDistance(b);
        r2 = r2 * r2;
        fF = (gG * this.mass * b.mass)/r2;
        return fF;
    }
    public double calcForceExertedByX(Body b){
        double r,fF, fFx,dx;
        r = this.calcDistance(b);
        dx = b.xxPos - this.xxPos;
        fF = this.calcForceExertedBy(b);
        fFx = fF * dx / r;
        return fFx;
    }
    public double calcForceExertedByY(Body b){
        double r,fF, fFy,dy;
        r = this.calcDistance(b);
        dy = b.yyPos - this.yyPos;
        fF = this.calcForceExertedBy(b);
        fFy = fF * dy / r;
        return fFy;
    }
    public double calcNetForceExertedByX(Body[] allbodys){
        double netFx = 0;
        for(Body b:allbodys){
            if (b.equals(this)){
                continue;
            }
            netFx += this.calcForceExertedByX(b);
        }
        return netFx;
    }
    public double calcNetForceExertedByY(Body[] allbodys){
        double netFy = 0;
        for(Body b:allbodys){
            if (b.equals(this)){
                continue;
            }
            netFy += this.calcForceExertedByY(b);
        }
        return netFy;
    }
    public void update(double dt,double fX,double fY){
        double anetx,anety;
        anetx = fX/this.mass;
        anety = fY/this.mass;
        this.xxVel += dt * anetx;
        this.yyVel += dt * anety;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);;
    }

}
