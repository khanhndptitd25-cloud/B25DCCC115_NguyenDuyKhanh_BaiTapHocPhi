class SinhVien {
    private String maSV;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSV, String hoTen, String ngaySinh) {
        this.maSV = maSV.trim();
        this.hoTen = chuanHoaTen(hoTen);
        this.ngaySinh = chuanHoaNgay(ngaySinh);
    }

    private String chuanHoaTen(String s) {
        String[] tu = s.trim().toLowerCase().split("\\s+");
        String ketQua = "";
        for (int i = 0; i < tu.length; i++) {
            if (tu[i].length() > 0) {
                String chuDau = tu[i].substring(0, 1).toUpperCase();
                String phanConLai = tu[i].substring(1);
                if (ketQua.length() > 0) {
                    ketQua = ketQua + " ";
                }
                ketQua = ketQua + chuDau + phanConLai;
            }
        }
        return ketQua;
    }

    private String chuanHoaNgay(String s) {
        String[] p = s.trim().split("/");
        String ngay = p[0].trim();
        String thang = p[1].trim();
        String nam = p[2].trim();

        if (ngay.length() == 1) {
            ngay = "0" + ngay;
        }
        if (thang.length() == 1) {
            thang = "0" + thang;
        }
        return ngay + "/" + thang + "/" + nam;
    }

    public void inThongTin() {
        System.out.println(maSV + " " + hoTen + " " + ngaySinh);
    }
}

public class Main {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        SinhVien[] ds = new SinhVien[n];

        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ns = sc.nextLine();
            ds[i] = new SinhVien(ma, ten, ns);
        }

        for (int i = 0; i < n; i++) {
            ds[i].inThongTin();
        }
        sc.close();
    }
}