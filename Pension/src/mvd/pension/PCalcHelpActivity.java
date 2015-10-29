package mvd.pension;

import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class PCalcHelpActivity extends SingleFragmentActivity {
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// Будет реализовано позднее
			if (NavUtils.getParentActivityName(this) != null) {
				NavUtils.navigateUpFromSameTask(this);			
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new PCalcHelpFragment();
	}

}
