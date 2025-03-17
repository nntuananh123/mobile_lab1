package com.example.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gán dữ liệu động (nếu cần)
        TextView name = findViewById(R.id.name);
        TextView jobTitle = findViewById(R.id.job_title);
        TextView contact = findViewById(R.id.contact);
        TextView experience = findViewById(R.id.experience);
        TextView skills = findViewById(R.id.skills);
        ImageView profileImage = findViewById(R.id.profile_image);

        // Đổi dữ liệu (giả lập)
        name.setText("Nguyễn Ngọc Tuấn Anh");
        jobTitle.setText("Android Developer");
        contact.setText("📧 Email: 21521834@gm.uit.edu.vn\n📞 Phone: 0966077968");
        experience.setText("• Developer tại UIT\n• 10 năm kinh nghiệm Flutter");
        skills.setText("• Java, Kotlin, Jetpack Compose\n• Firebase, Room Database\n• Git, Agile");

        // Thay ảnh đại diện (giả lập)
        profileImage.setImageResource(R.drawable.avatar); // Thay ảnh trong drawable
    }
}
