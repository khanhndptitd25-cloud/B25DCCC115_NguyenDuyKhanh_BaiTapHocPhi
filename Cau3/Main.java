class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

abstract class SinhVien {
    protected String maSV;
    protected String maHe;
    protected int soTinChi;

    public SinhVien(String maSV, String maHe, int soTinChi) {
        this.maSV = maSV;
        this.maHe = maHe;
        this.soTinChi = soTinChi;
    }

    public String getMaSV() {
        return maSV;
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

class QuanLyHocPhi {
    private java.util.ArrayList<SinhVien> ds = new java.util.ArrayList<SinhVien>();

    private boolean daTonTai(String maSV) {
        for (int i = 0; i < ds.size(); i++) {
            if (ds.get(i).getMaSV().equals(maSV)) {
                return true;
            }
        }
        return false;
    }

    public void themSinhVien(String maHe, String maSV, String tinChiStr) throws DuLieuKhongHopLeException {
        if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
            throw new DuLieuKhongHopLeException("Sai ma he");
        }

        int tinChi;
        try {
            tinChi = Integer.parseInt(tinChiStr);
        } catch (NumberFormatException e) {
            throw new DuLieuKhongHopLeException("Khong phai so nguyen");
        }

        if (tinChi < 1 || tinChi > 30) {
            throw new DuLieuKhongHopLeException("So tin chi ngoai [1, 30]");
        }

        if (daTonTai(maSV)) {
            throw new DuLieuKhongHopLeException("Trung ma sinh vien");
        }

        if (maHe.equals("CQ")) {
            ds.add(new SinhVienCQ(maSV, tinChi));
        } else if (maHe.equals("CLC")) {
            ds.add(new SinhVienCLC(maSV, tinChi));
        } else {
            ds.add(new SinhVienLT(maSV, tinChi));
        }
    }

    public void sapXep() {
        for (int i = 0; i < ds.size() - 1; i++) {
            for (int j = i + 1; j < ds.size(); j++) {
                SinhVien a = ds.get(i);
                SinhVien b = ds.get(j);
                boolean doiCho = false;

                if (a.hocPhi() < b.hocPhi()) {
                    doiCho = true;
                } else if (a.hocPhi() == b.hocPhi() && a.getMaSV().compareTo(b.getMaSV()) > 0) {
                    doiCho = true;
                }

                if (doiCho) {
                    ds.set(i, b);
                    ds.set(j, a);
                }
            }
        }
    }

    public void inDanhSachVaTong() {
        long tong = 0;
        for (int i = 0; i < ds.size(); i++) {
            ds.get(i).inThongTin();
            tong = tong + ds.get(i).hocPhi();
        }
        System.out.println("TONG " + tong);
    }
}

public class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int n = sc.nextInt();

        QuanLyHocPhi ql = new QuanLyHocPhi();
        String[] dsLoi = new String[n];
        int soLoi = 0;

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSV = sc.next();
            String tinChiStr = sc.next();

            try {
                ql.themSinhVien(maHe, maSV, tinChiStr);
            } catch (DuLieuKhongHopLeException e) {
                dsLoi[soLoi] = "INVALID " + maSV;
                soLoi++;
            }
        }

        for (int i = 0; i < soLoi; i++) {
            System.out.println(dsLoi[i]);
        }

        ql.sapXep();
        ql.inDanhSachVaTong();
        sc.close();
    }
}