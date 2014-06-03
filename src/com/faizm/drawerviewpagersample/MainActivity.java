package com.faizm.drawerviewpagersample;

import java.util.ArrayList;
import java.util.List;

import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
import com.viewpagerindicator.UnderlinePageIndicator;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{

	ViewPagerAdapterOne mAdapterOne;
	ViewPagerAdapterTwo mAdapterTwo;
	ViewPager mViewPager;
	List<Fragment> mFragmentsListOne = new ArrayList<Fragment>();
	List<Fragment> mFragmentsListTwo = new ArrayList<Fragment>();
	UnderlinePageIndicator indicator;
	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFragmentsListOne = getFragmentsListOne();
		mFragmentsListTwo = getFragmentsListTwo();
		mAdapterOne = new ViewPagerAdapterOne(getFragmentManager(), mFragmentsListOne);
		mAdapterTwo = new ViewPagerAdapterTwo(getFragmentManager(), mFragmentsListTwo);
		mViewPager = (ViewPager) findViewById(R.id.mpager);
		mViewPager.setAdapter(mAdapterOne);
		indicator = (UnderlinePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
        indicator.setFades(false);
        indicator.setSelectedColor(Color.WHITE);
		
        mPlanetTitles = new String[] { "Blue", "Red" };
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mPlanetTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close)
		{
			public void onDrawerClosed(View view)
			{
				getActionBar().setTitle("DrawerPagerSample");
			}

			public void onDrawerOpened(View drawerView)
			{
				getActionBar().setTitle("Drawer Open");
			}
		};
		mDrawerList.setItemChecked(0, true);
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (mDrawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public List<Fragment> getFragmentsListOne()
	{
		List<Fragment> mFragmentsListTemp = new ArrayList<Fragment>();
		mFragmentsListTemp.add(Fragment.instantiate(getApplicationContext(), FirstAdapterFragment.class.getName()));
		mFragmentsListTemp.add(Fragment.instantiate(getApplicationContext(), SecondAdapterFragment.class.getName()));
		return mFragmentsListTemp;
	}

	public List<Fragment> getFragmentsListTwo()
	{
		List<Fragment> mFragmentsListTemp22 = new ArrayList<Fragment>();
		mFragmentsListTemp22.add(Fragment.instantiate(getApplicationContext(), SecondAdapterFragment.class.getName()));
		mFragmentsListTemp22.add(Fragment.instantiate(getApplicationContext(), FirstAdapterFragment.class.getName()));
		return mFragmentsListTemp22;
	}

	@Override
	public void setTitle(CharSequence title)
	{
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id)
		{
			mDrawerList.setItemChecked(position, true);
			setTitle(mPlanetTitles[position]);
			if(position == 0)
			{
				mViewPager.setAdapter(mAdapterOne);
				mViewPager.invalidate();
				mViewPager.setCurrentItem(0);
			}
			else
			if(position == 1)
			{
				mViewPager.setAdapter(mAdapterTwo);
				mViewPager.invalidate();
				mViewPager.setCurrentItem(0);
			}
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

}
