package com.faizm.drawerviewpagersample;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

//test
class ViewPagerAdapterOne extends FragmentStatePagerAdapter
{
	List<Fragment> mFragments;
	String adapterName;

	public ViewPagerAdapterOne(FragmentManager mFragManager, List<Fragment> mFragment) 
	{
		super(mFragManager);
		this.mFragments = mFragment;
	}
	
	@Override
	public int getCount() 
	{
		return mFragments.size();
	}
	
	
	public String getAdapterName()
	{
		return adapterName;
	}
	
	@Override
	public Fragment getItem(int position) 
	{
		return mFragments.get(position);
	}
	
}
