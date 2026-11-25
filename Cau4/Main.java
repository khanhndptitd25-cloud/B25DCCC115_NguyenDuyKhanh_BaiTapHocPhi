interface MienGiam {
    long tinhGiam(long hocPhi);
}

class MienGiamCS implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 50 / 100;
    }
}

class MienGiamHN implements MienGiam {
    public long tinhGiam(long hocPhi) {
        return hocPhi * 70 / 100;
    }
}

class MienGiamHB implements MienGiam {
    public long tinhGiam(long hocPhi) {
        if (hocPhi < 3000000L) {
            return hocPhi;
        }
        return 3000000L;
    }
}

abstract class SinhVien {
    protected String maSV;
    protected String maHe;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getMaHe() {
        return maHe;
    }

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public abstract long hocPhi();

    public long tienGiam() {
        if (mienGiam == null) {
            return 0;
        }
        return mienGiam.tinhGiam(hocPhi());
    }

    public long phaiNop() {
        return hocPhi() - tienGiam();
    }

    public void inThongTin() {
        long hp = hocPhi();
        long giam = tienGiam();
        long nop = hp - giam;
        System.out.println(maSV + " " + maHe + " " + hp + " " + giam + " " + nop);
    }
}

class SinhVienCQ extends SinhVien {
    public SinhVienCQ(String maSV, int soTinChi) {
        super(maSV, "CQ", soTinChi);
    }

    public long hocPhi() {
        return soTinChi * 450000L;
    }
}

class SinhVienCLC extends SinhVien {
    public SinhVienCLC(String maSV, int soTinChi) {
        super(maSV, "CLC", soTinChi);
    }

    public long hocPhi() {
        return soTinChi * 450000L * 3 / 2;
    }
}

class SinhVienLT extends SinhVien {
    public SinhVienLT(String maSV, int soTinChi) {
        super(maSV, "LT", soTinChi);
    }

    public long hocPhi() {
        return soTinChi * 450000L + 2000000L;
    }
}

public class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int n = sc.nextInt();
        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            int tinChi = sc.nextInt();

            if (maHe.equals("CQ")) {
                ds[i] = new SinhVienCQ(maSV, tinChi);
            } else if (maHe.equals("CLC")) {
                ds[i] = new SinhVienCLC(maSV, tinChi);
            } else if (maHe.equals("LT")) {
                ds[i] = new SinhVienLT(maSV, tinChi);
            }
        }

        int m = sc.nextInt();
        String[] notFound = new String[m];
        int soNotFound = 0;

        for (int i = 0; i < m; i++) {
            String maSV = sc.next();
            String maCS = sc.next();

            int viTri = -1;
            for (int j = 0; j < n; j++) {
                if (ds[j].getMaSV().equals(maSV)) {
                    viTri = j;
                    break;
                }
            }

            if (viTri == -1) {
                notFound[soNotFound] = "NOT FOUND " + maSV;
                soNotFound++;
            } else {
                if (maCS.equals("CS")) {
                    ds[viTri].setMienGiam(new MienGiamCS());
                } else if (maCS.equals("HN")) {
                    ds[viTri].setMienGiam(new MienGiamHN());
                } else if (maCS.equals("HB")) {
                    ds[viTri].setMienGiam(new MienGiamHB());
                }
            }
        }

        for (int i = 0; i < soNotFound; i++) {
            System.out.println(notFound[i]);
        }

        for (int i = 0; i < n; i++) {
            ds[i].inThongTin();
        }

        String[] cacHe = {"CQ", "CLC", "LT"};
        for (int i = 0; i < cacHe.length; i++) {
            int dem = 0;
            long tongNop = 0;
            for (int j = 0; j < n; j++) {
                if (ds[j].getMaHe().equals(cacHe[i])) {
                    dem++;
                    tongNop = tongNop + ds[j].phaiNop();
                }
            }
            if (dem > 0) {
                System.out.println(cacHe[i] + " " + dem + " " + tongNop);
            }
        }

        sc.close();
    }
}