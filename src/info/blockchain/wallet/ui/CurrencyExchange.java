package info.blockchain.wallet.ui;

import java.util.HashMap;

import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.util.Log;

import info.blockchain.api.ExchangeRates;

public class CurrencyExchange	{

    private static CurrencyExchange instance = null;
    
    private static ExchangeRates fxRates = null;
    private static HashMap<String,Double> prices = null;
    private static HashMap<String,String> symbols = null;

    private static Context context = null;
    
    private CurrencyExchange()	{ ; }

	public static CurrencyExchange getInstance(Context ctx) {
		
		context = ctx;

		fxRates = new ExchangeRates();

		if (instance == null) {
		    prices = new HashMap<String,Double>();
		    symbols = new HashMap<String,String>();
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
			String[] currencies = fxRates.getCurrencies();
	    	for(int i = 0; i < currencies.length; i++)	 {
		    	prices.put(currencies[i], Double.longBitsToDouble(prefs.getLong(currencies[i], Double.doubleToLongBits(0.0))));
		    	symbols.put(currencies[i], prefs.getString(currencies[i] + "-SYM", null));
	    	}

	    	instance = new CurrencyExchange();
		}

		getExchangeRates();

		return instance;
	}
	
    public Double getCurrencyPrice(String currency)	{
    	
    	if(prices.containsKey(currency) && prices.get(currency) != 0.0)	{
    		return prices.get(currency);
    	}
    	else	{
    		return 0.0;
    	}

    }

    public String getCurrencySymbol(String currency)	{
    	
    	if(symbols.containsKey(currency) && symbols.get(currency) != null)	{
    		return symbols.get(currency);
    	}
    	else	{
    		return null;
    	}

    }

	private static void getExchangeRates() {
        DownloadFXRatesTask task = new DownloadFXRatesTask(context, fxRates);
        task.execute(new String[] { fxRates.getUrl() });
	}

}