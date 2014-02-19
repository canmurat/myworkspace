package com.kodlab.dictionary;

import android.net.Uri;
import android.provider.BaseColumns;

public final class DictionaryContract {

	public static final String AUTHORITY = "com.kodlab.dictionary.provider.DictionaryProvider";	
	public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
	
	public static final String DATABASE_NAME = "sozluk";
	public static final String TABLE_NAME = "kelime";
	public static final int DATABASE_VERSION = 1;
	
	public static class Kelime implements BaseColumns {
		
		private Kelime() {}
		
		public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, "kelime");
		
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.kodlab.dictionary.provider.DictionaryProvider.kelime";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.kodlab.dictionary.provider.DictionaryProvider.kelime";
		
		public static final String COLUMN_AD = "ad";
		public static final String COLUMN_ACIKLAMA = "aciklama";
		
		public static final String DEFAULT_SORT_ORDER = "ad ASC";
		
		public static final String[] FULL_PROJECTION = new String[] {_ID, COLUMN_AD, COLUMN_ACIKLAMA};
		
	}
	
}
