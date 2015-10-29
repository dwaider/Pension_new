package mvd.pension;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;


public class PCalcPensDataFragment extends Fragment {
	private PCalc pens;
	private EditText pOkladZvan;
	private EditText pOkladDolg;
	private EditText pProcentNadbv;
	private EditText pKalendVisl;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		pens = PCalc.get(getActivity());
		setHasOptionsMenu(true);
    }
	
	@TargetApi(11)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_pens_calc, parent, false);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		pOkladZvan = (EditText)v.findViewById(R.id.edPOklad_zvan);
		pOkladDolg = (EditText)v.findViewById(R.id.edPOklad_dolg);
		pProcentNadbv = (EditText)v.findViewById(R.id.edPProcent_nadb);
		pKalendVisl = (EditText)v.findViewById(R.id.edKalendVisl);

		if (pens.getpOkladZvani() != 0)  pOkladZvan.setText(String.valueOf((int)pens.getpOkladZvani()));
		if (pens.getpOkladDolg() != 0) pOkladDolg.setText(String.valueOf((int)pens.getpOkladDolg()));
		if (pens.getVislLetPoln() != 0) pProcentNadbv.setText(String.valueOf((int)pens.getVislLetPoln()));
		if (pens.getpKlandVisl() != 0) pKalendVisl.setText(String.valueOf((int)pens.getpKlandVisl()));
		
		pOkladZvan.addTextChangedListener(new GenericTextWatcher(pOkladZvan));
		pOkladDolg.addTextChangedListener(new GenericTextWatcher(pOkladDolg));
		pProcentNadbv.addTextChangedListener(new GenericTextWatcher(pProcentNadbv));
		pKalendVisl.addTextChangedListener(new GenericTextWatcher(pKalendVisl));
		pKalendVisl.setOnFocusChangeListener(new GenFocus());
		return v;
	}
	
	//Declaration
	private class GenericTextWatcher implements TextWatcher{
	    private View view;
	    private GenericTextWatcher(View view) {
	        this.view = view;
	    }
	    @Override
	    public void beforeTextChanged(CharSequence ch, int i, int i1, int i2) {
	
	    }
	    public void onTextChanged(CharSequence ch, int i, int i1, int i2) {
	    	try {
		            Integer TextToFloat = Integer.parseInt(ch.toString());
			        switch(view.getId()){
			            case R.id.edPOklad_dolg:
			                pens.setpOkladDolg(TextToFloat);
			                break;
			            case R.id.edPOklad_zvan:
			                pens.setpOkladZvani(TextToFloat);
			                break;
			            case R.id.edPProcent_nadb:
			                pens.setpVislLet(TextToFloat);
			                break;
			            case R.id.edKalendVisl:
			                pens.setpKlandVisl(TextToFloat);
			                break;			                
		        }
			} catch (Exception e) {
				// TODO: handle exception
				//Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
			}

	    }
	    @Override
	    public void afterTextChanged(Editable edTt) {
	    	
	    }
	}
	
	
	private class GenFocus implements View.OnFocusChangeListener{
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			// TODO Auto-generated method stub
			if (!hasFocus) {
		        switch(v.getId()){
	                case R.id.edKalendVisl:
	                   if (pens.getpKlandVisl()< 20) {
	                	   ((EditText)v).setError("Право на пенсию не наступило.");
	                   }
	                   break;
		        }
			}
		}
	}
}
