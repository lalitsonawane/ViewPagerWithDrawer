package com.faizm.drawerviewpagersample;

import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;


class ViewPagerAdapterTwo extends FragmentStatePagerAdapter
{
	List<Fragment> mFragments;
	String adapterName;

	public ViewPagerAdapterTwo(FragmentManager mFragManager, List<Fragment> mFragment) 
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