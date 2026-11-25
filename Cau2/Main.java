abstract class SinhVien {
    protected String maSV;
    protected String maHe;
    protected int soTinChi;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public void inThongTin() {
        System.out.println(maSV + " " + maHe + " " + hocPhi());
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

        for (int i = 0; i < n; i++) {
            ds[i].inThongTin();
        }
        sc.close();
    }
}