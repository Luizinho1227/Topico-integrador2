package com.example.apppizza;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CourseRVAdapter extends ListAdapter<CourseModal, CourseRVAdapter.ViewHolder> {
    private OnItemClickListener listener;

    CourseRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<CourseModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<CourseModal>() {
        @Override
        public boolean areItemsTheSame(CourseModal oldItem, CourseModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(CourseModal oldItem, CourseModal newItem) {
            return oldItem.getCourseName().equals(newItem.getCourseName()) &&
                    oldItem.getCourseDescription().equals(newItem.getCourseDescription()) &&
                    oldItem.getCourseDuration().equals(newItem.getCourseDuration());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseModal model = getCourseAt(position);
        holder.courseNameTV.setText(model.getCourseName());
        holder.courseDescTV.setText(model.getCourseDescription());
        holder.courseDurationTV.setText(model.getCourseDuration());
    }

    public CourseModal getCourseAt(int position) {
        return getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseNameTV, courseDescTV, courseDurationTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseNameTV = itemView.findViewById(R.id.idTVNomeCurso);
            courseDescTV = itemView.findViewById(R.id.idTVDescricaoCurso);
            courseDurationTV = itemView.findViewById(R.id.idTVDuracaoCurso);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CourseModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

