package customUI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.example.transtion.my5th.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by 不爱白菜 on 2016/6/27.
 */
    public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
        public PullToRefreshRecyclerView(Context context) {
            super(context);
        }

        public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public PullToRefreshRecyclerView(Context context, Mode mode) {
            super(context, mode);
        }

        public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle style) {
            super(context, mode, style);
        }

        public final Orientation getPullToRefreshScrollDirection() {
            return Orientation.VERTICAL;
        }

        protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
//            RecyclerView recyclerView = (RecyclerView) View.inflate(context,R.layout.fragment_o_other_recycleview,null);
            RecyclerView recyclerView =new RecyclerView(context,attrs);
            recyclerView.setId(R.id.recycleview);
            return recyclerView;
        }

        protected boolean isReadyForPullStart() {
            return this.isFirstItemVisible();
        }

        protected boolean isReadyForPullEnd() {
            return this.isLastItemVisible();
        }

        private boolean isFirstItemVisible() {
            RecyclerView.Adapter adapter = ((RecyclerView)this.getRefreshableView()).getAdapter();
            return null != adapter && adapter.getItemCount() != 0?(this.getFirstVisiblePosition() == 0?((RecyclerView)this.mRefreshableView).getChildAt(0).getTop() >= ((RecyclerView)this.mRefreshableView).getTop():false):true;
        }

        private int getFirstVisiblePosition() {
            View firstVisibleChild = ((RecyclerView)this.mRefreshableView).getChildAt(0);
            return firstVisibleChild != null?((RecyclerView)this.mRefreshableView).getChildAdapterPosition(firstVisibleChild):-1;
        }

        private boolean isLastItemVisible() {
            RecyclerView.Adapter adapter = ((RecyclerView)this.getRefreshableView()).getAdapter();
            if(null != adapter && adapter.getItemCount() != 0) {
                int lastVisiblePosition = this.getLastVisiblePosition();
                return lastVisiblePosition >= ((RecyclerView)this.mRefreshableView).getAdapter().getItemCount() - 1?((RecyclerView)this.mRefreshableView).getChildAt(((RecyclerView)this.mRefreshableView).getChildCount() - 1).getBottom() <= ((RecyclerView)this.mRefreshableView).getBottom():false;
            } else {
                return true;
            }
        }

        private int getLastVisiblePosition() {
            View lastVisibleChild = ((RecyclerView)this.mRefreshableView).getChildAt(((RecyclerView)this.mRefreshableView).getChildCount() - 1);
            return lastVisibleChild != null?((RecyclerView)this.mRefreshableView).getChildAdapterPosition(lastVisibleChild):-1;
        }


}
