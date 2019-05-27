package fgoScript.entity.panel;

import aoshiScript.entity.WuNa;
import com.github.houbb.markdown.toc.core.impl.AtxMarkdownToc;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import commons.entity.NativeCp;
import commons.util.MySpringUtil;
import fgoScript.entity.Gudazi;
import fgoScript.entity.BaseZButton;
import fgoScript.entity.Zpanel;
import commons.util.GameUtil;
import commons.util.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
/**
 * @description: Fgo界面框架
 * @author: RENZHEHAO
 * @create: 2019-05-22 03:45
 **/
public class FgoFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static FgoFrame f;
	public static FgoFrame instance() {
		if (f == null) {
			f = (FgoFrame) MySpringUtil.getApplicationContext().getBean("fgoFrame");
			return f;
		}else {
			return f;
		}
	}

	private WuNa wuna;

	public void setWuna(WuNa wuna) {
		this.wuna = wuna;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
	}

	/**
	 * @Description: 初始化init() 构造函数
	 * @Author: RENZHEHAO
	 * @Date: 2019/5/22
	 */
	public FgoFrame() {
		init();
	}
	private List<BaseZButton> getListFromJsonFile(){
		return null;
	}
	private final BaseZButton[] bts = {
			new BaseZButton(null, 0,"(小号)材料所有号",JIntellitype.MOD_SHIFT, (int) 'P',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 3981539681889014623L;
				@Override
				public void runMethod() throws Exception {

					new Gudazi().trainSamllFgo();
				}
			},
			new BaseZButton(null, 0,"(小号+主号)领取奖励抽免费池",JIntellitype.MOD_SHIFT, (int) 'L',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 8200576435327725059L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().allRewardAndRoll();
				}
			},
			new BaseZButton(null, 0,"(小号+主号)签到所有号",JIntellitype.MOD_SHIFT, (int) 'S',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 53933841194261802L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().signAllFGO();
				}
			},
			new BaseZButton(null, 0,"(小号+主号)执行所有",JIntellitype.MOD_SHIFT, (int) 'T',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 2069565531679104865L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().goAll();
				}
			},
			new BaseZButton(null, 0,"(账号10)开始刷活动(Shift+K)",JIntellitype.MOD_SHIFT, (int) 'K',false, false, BaseZButton.pink) {
				private static final long serialVersionUID = 1369499050729104861L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().eventingFgo();
				}
			},
			new BaseZButton(null, 0,"(账号10)无限刷qp",JIntellitype.MOD_SHIFT, (int) 'Q',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = -7555639858773795825L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().mainAccountQP40();
				}
			},
			new BaseZButton(null, 0,"(账号10)无限刷EXP",JIntellitype.MOD_SHIFT, (int) 'P',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 6504858991507730448L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().mainAccountEXP40();
				}
			},
			new BaseZButton(null, 0,"获取鼠标位置颜色(Shift+E)",JIntellitype.MOD_SHIFT, (int) 'E',false, false, BaseZButton.pink) {
				private static final long serialVersionUID = -7389326247723796445L;
				@Override
				public void runMethod() {
					new Gudazi().showPositionAndColor();
				}
			},
			new BaseZButton(null, 0,"开启账号",JIntellitype.MOD_SHIFT, (int) 'O',true, false, BaseZButton.pink) {
				private static final long serialVersionUID = 432750026975346042L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().openEvent();
				}
			},
			new BaseZButton(null, 0,"自动战斗(Alt+Z)",JIntellitype.MOD_ALT, (int) 'Z',false, false, BaseZButton.pink) {
				private static final long serialVersionUID = -7389326247723796445L;
				@Override
				public void runMethod() throws Exception {
					new Gudazi().onlyFight();
				}
			},
			new BaseZButton(null, 0,"自动按键(Alt+X/1)",JIntellitype.MOD_ALT, (int) 'X', false, false, BaseZButton.pink) {
				private static final long serialVersionUID = -1900866258318497377L;
				@Override
				public void runMethod() {
					wuna.alwaysClick();
				}
			},
			new BaseZButton("skills01",0," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 7839671713774463047L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills01",1," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7414070501363863901L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills01",2," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 4355835973077552249L;

				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills02",0," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 4947072762437664728L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills02",1," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 7846182532074063143L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills02",2," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 9020476815603182132L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills03",0," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 1575767177788722669L;
				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills03",1," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 638308570629886471L;

				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton("skills03",2," ", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 8648030433776394191L;

				@Override
				public void runMethod() {
					this.activeButton();
				}
			},new BaseZButton(null, 0,"monster01", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -2891221341872701498L;
				@Override
				public void runMethod() {
					this.selectMonster(0);
				}
			}
			,new BaseZButton(null, 0, "monster02", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -3891221341872701498L;
				@Override
				public void runMethod() {
					this.selectMonster(1);
				}
			}
			,new BaseZButton(null, 0,"monster03", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7491221341872701498L;
				@Override
				public void runMethod() {
					this.selectMonster(2);
				}
			}
			,new BaseZButton(null, 0,"person01", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 8554261580079318513L;

				@Override
				public void runMethod() {
					this.selectPerson(0);
				}
			},new BaseZButton(null, 0,"person02", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7891221341872701498L;
				@Override
				public void runMethod() {
					this.selectPerson(1);
				}
			},new BaseZButton(null, 0,"person03", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -1891221341872701498L;
				@Override
				public void runMethod() {
					this.selectPerson(2);
				}
			}
			,new BaseZButton(null, 0,"终止", JIntellitype.MOD_ALT, KeyEvent.VK_0, true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7491221341872701498L;
				@Override
				public void runMethod() {
					GameUtil.shutDownALl();
				}
			},new BaseZButton(null, 0,"暂停", JIntellitype.MOD_ALT, KeyEvent.VK_9, true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7491221341872701498L;
				@Override
				public void runMethod() {
					GameUtil.waitOrContinue(this);
				}
			},new BaseZButton(null, 0,"超时", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7491221341872701498L;
				@Override
				public void runMethod() {
					GameUtil.setFORCE_OUTTIME(true);
				}
			},new BaseZButton(null, 0,"新开", JIntellitype.MOD_SHIFT, (int) 'i', true, true, BaseZButton.pink) {
				private static final long serialVersionUID = -7491221341872701498L;
				@Override
				public void runMethod() {
					this.selectIfRestart();
				}
			},new BaseZButton(null, 0,"点击设置",JIntellitype.MOD_ALT, KeyEvent.VK_1,false, false, BaseZButton.pink) {
				private static final long serialVersionUID = 6504858991507730448L;
				@Override
				public void runMethod() {
					wuna.configClick(this);
				}
			},new BaseZButton(null, 0,"清空",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 6504858991507730448L;
				@Override
				public void runMethod() {
					wuna.falshClick(this);
				}
			},new BaseZButton(null, 0,"解除激活",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
				private static final long serialVersionUID = 6504858991507730448L;
				@Override
				public void runMethod() {
					deActiveAll();
				}
			},new BaseZButton(null, 0,"移动(S+W)",JIntellitype.MOD_SHIFT, (int) 'W',false, false, BaseZButton.pink) {
				private static final long serialVersionUID = -7389326247723792445L;
				@Override
				public void runMethod() {
					try {
						new Gudazi().moveToPositionByClipBoard();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
	};
	private final Zpanel backPanel = new Zpanel();
	private final Zpanel centerPanel = new Zpanel();
	private final Zpanel buttonPanel = new Zpanel();
	private final Zpanel southPanel01 = new Zpanel();
	private final Zpanel southPanel02 = new Zpanel();
	private final Zpanel southPanel03 = new Zpanel();
	private final Zpanel southPanel04 = new Zpanel();
	private final Zpanel eastPanel01 = new Zpanel();
	// 初始化
	private void init() {
		// 面板对象s
		backPanel.setLayout(new BorderLayout());
		centerPanel.setLayout(null);
		buttonPanel.setLayout(new GridLayout(2, 1));
		southPanel01.setLayout(new GridLayout(1, 9));
		southPanel02.setLayout(new GridLayout(1, 3));
		southPanel03.setLayout(new GridLayout(1, 3));
		southPanel04.setLayout(new GridLayout(1, 1));
		eastPanel01.setLayout(new GridLayout(1, 3));
		// 添加按钮
		BaseZButton jbTemp;
		int size = bts.length;
		int skillcount = 0;
		int len = 2;
		for (int i = 0; i < size; i++) {
			jbTemp = bts[i];
			jbTemp.setMarkCode(i);
			if (" ".equals(jbTemp.getText())) {
				jbTemp.setText(jbTemp.getSkilStateText());
				southPanel01.add(jbTemp);
				if (2 < skillcount && skillcount < 6) {
					jbTemp.setSkillsStatus(1);
				}else {
					jbTemp.setSkillsStatus(2);
				}
				skillcount++;
			}else if (jbTemp.getText().contains("monster")) {
				jbTemp.setText(jbTemp.getToMonsterText());
				southPanel03.add(jbTemp);
			}else if (jbTemp.getText().contains("person")) {
				jbTemp.setText(jbTemp.getToPersonText());
				southPanel02.add(jbTemp);
			}else if ("终止".equals(jbTemp.getText())) {
				jbTemp.setText("终止");
				addSouthPanle04(jbTemp);

			}else if ("暂停".equals(jbTemp.getText())) {
				jbTemp.setText("暂停");
				addSouthPanle04(jbTemp);

			}else if ("超时".equals(jbTemp.getText())) {
				jbTemp.setText("超时");
				southPanel04.add(jbTemp);
			}
			else if ("点击设置".equals(jbTemp.getText())) {
				jbTemp.setText("点击设置");
				eastPanel01.add(jbTemp);
			}
			else if ("清空".equals(jbTemp.getText())) {
				jbTemp.setText("重置");
				eastPanel01.add(jbTemp);
			}
			else if ("解除激活".equals(jbTemp.getText())) {
				jbTemp.setText("解除激活");
				jbTemp.setVerticalTextPosition(SwingConstants.CENTER);
				eastPanel01.add(jbTemp);
			}
			else if ("新开".equals(jbTemp.getText())) {
				jbTemp.setText(jbTemp.getReStartFlag());
				southPanel04.add(jbTemp);
			}
			else if (jbTemp.getText().contains("S+W")) {
				jbTemp.setBounds(220, 5 + (7 * (30+len)), 105, 25+len);
				centerPanel.add(jbTemp);
			}
			else {
				if ("开启账号".equals(jbTemp.getText())) {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 210, 25+len);
				} else if (jbTemp.getText().contains("自动战斗")) {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 210, 25+len);
				} else if (jbTemp.getText().contains("获取鼠标")) {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 210, 25+len);
				} else if (jbTemp.getText().contains("刷EXP")) {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 210, 25+len);
				} else if (jbTemp.getText().contains("自动按键")) {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 210, 25+len);
					jbTemp.setAllowRepeat(true);
				} else {
					jbTemp.setBounds(5, 5 + (i * (30+len)), 320, 25+len);
				}
				centerPanel.add(jbTemp);
			}
		}
		//选择账号按钮
		jbTemp = new BaseZButton(null, 0,"",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
			private static final long serialVersionUID = 8438279990543323489L;
			@Override
			public void runMethod() {
				this.selectAccount();
			}
		};
		int account = getAccount();
		jbTemp.setText("账号" + account);
		jbTemp.setBounds(220, 5 + (8 * (30+len)), 105, 25+len);
		centerPanel.add(jbTemp);
		//选择战斗策略按钮
		jbTemp = new BaseZButton(null, 0,"",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
			private static final long serialVersionUID = 8438279990546323489L;
			@Override
			public void runMethod() {
				this.selectBattleStrategy();
			}
		};
		jbTemp.setText(jbTemp.getskillStrategy());
		jbTemp.setBounds(220, 5 + (9 * (30+len)), 105, 25+len);
		centerPanel.add(jbTemp);
		//按键类型按钮
		List<String> changeList = new ArrayList<>();
		changeList.add("左键");
		changeList.add("判断");
		jbTemp = BaseZButton.getChangeListButton(changeList,"changeButton_" + NativeCp.getUserName(), "clickStrategy", true, true, BaseZButton.pink);
		jbTemp.setBounds(220, 5 + (10 * (30+len)), 50, 25+len);
        centerPanel.add(jbTemp);
		//按键倍率按钮
        changeList = new ArrayList<>();
        changeList.add("0倍");
        changeList.add("1倍");
        changeList.add("2倍");
        changeList.add("3倍");
        jbTemp = BaseZButton.getChangeListButton(changeList,"changeButton_" + NativeCp.getUserName(), "multiFactor", true, true, BaseZButton.pink);
		jbTemp.setBounds(275, 5 + (10 * (30+len)), 50, 25+len);
		centerPanel.add(jbTemp);
		//显示背景按钮
		BaseZButton showPicBt = new BaseZButton(null, 0,"显示背景",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
			private static final long serialVersionUID = 6504858991507730448L;
			@Override
			public void runMethod() {
				showBackGround();
			}
		};
		eastPanel01.add(showPicBt);
		//切换背景按钮
		BaseZButton changePicBt = new BaseZButton(null, 0,"切换背景",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
			private static final long serialVersionUID = 6504858991507730448L;
			@Override
			public void runMethod() {
				String dir = System.getenv("USERPROFILE") + "\\OneDrive\\图片\\桌面背景";
                System.out.println(dir);

                FileDialog fd = new FileDialog(JOptionPane.getFrameForComponent(centerPanel), "选择背景");
				fd.setDirectory(dir);
				fd.setVisible(true);
                fd.setFilenameFilter(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        if (name.contains(".jpg")){
                            return true;
                        }
                        return false;
                    }
                });
				fd.setResizable(true);
				changeBackGroundByPath( fd.getDirectory()+ "\\" + fd.getFile());
                fd.setFile(fd.getDirectory()+ "\\" + fd.getFile());
			}
		};
		eastPanel01.add(changePicBt);
		//MD文件生成目录按钮
		BaseZButton exportCatalog = new BaseZButton(null, 0,"md目录",JIntellitype.MOD_SHIFT, (int) 'P',true, true, BaseZButton.pink) {
			private static final long serialVersionUID = 6504858991507730848L;
			@Override
			public void runMethod() {

                FileDialog fd = new FileDialog(JOptionPane.getFrameForComponent(centerPanel), "选择md文件");
				fd.setVisible(true);
                fd.setFilenameFilter(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        if (name.contains(".md")){
                            return true;
                        }
                        return false;
                    }
                });
				fd.setResizable(true);
				String path = fd.getDirectory()+ "\\" + fd.getFile();
				File tempFile = new File(path);
				if (tempFile.exists()){
					//生成md文件的目录
					AtxMarkdownToc.newInstance().genTocFile(fd.getDirectory()+ "\\" + fd.getFile());
				}
                fd.setFile(fd.getDirectory()+ "\\" + fd.getFile());
			}
		};
		exportCatalog.setBounds(220, 5 + (6 * (30+len)), 105, 25+len);
		centerPanel.add(exportCatalog);
		centerPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		southPanel01.setOpaque(false);
		southPanel02.setOpaque(false);
		southPanel03.setOpaque(false);
		southPanel04.setOpaque(false);
		eastPanel01.setOpaque(false);
		buttonPanel.add(southPanel01);
		buttonPanel.add(southPanel04);
		buttonPanel.add(southPanel02);
		buttonPanel.add(southPanel03);
		buttonPanel.setBounds(5, 10 + (12 * (30+len)), 320, 55+len);
		centerPanel.add(buttonPanel);
		eastPanel01.setBounds(5, 5 + (11 * (30+len)), 320, 25+len);
        eastPanel01.setName("bottomButtons");
		centerPanel.add(eastPanel01);
		backPanel.add(centerPanel);
		backPanel.setBackground(GameUtil.getBackGroundPreFix((int)(333*1.1), (int)(470*1.1)));

		this.add(backPanel);
		this.setTitle("FGO-JAI");
		this.setSize(335, 500);
		this.setLocation(1537, 516);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(true);

		// 添加热键监听器
		HotkeyListener hotkeyListener = markCode -> {
			BaseZButton zbt = bts[markCode];
			new Thread(zbt).start();
		};
		JIntellitype.getInstance().addHotKeyListener(hotkeyListener);
	}

	public static int getAccount() {
		String openAccountStr = PropertiesUtil.getValueFromOpenFile("openAccount");
		int[] FgoRewardArray = GameUtil.strToIntArray(GameUtil.getValueFromConfig("FgoRewardArray"),true);
		return StringUtils.isBlank(openAccountStr)?
					FgoRewardArray[0]:
					Integer.valueOf(openAccountStr);
	}

	private void addSouthPanle04(BaseZButton jbTemp) {
		jbTemp.setAllowRepeat(true);
		JIntellitype.getInstance().registerHotKey(jbTemp.getMarkCode(), jbTemp.getShortcunt01(), jbTemp.getShortcunt02());
		southPanel04.add(jbTemp);
	}

	private void showBackGround() {
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
		ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
		singleThreadPool.execute(()-> {
            centerPanel.setVisible(true);
            Component[] comps = centerPanel.getComponents();
            int size = comps.length;
            Component com;
            BaseZButton zt;
            Component[] temps;
            int len;
            for (int i = 0; i < size; i++) {
                com = comps[i];
                if ("bottomButtons".equals(com.getName())){
                    if ( com instanceof Zpanel){
                        temps = ((Zpanel) com).getComponents();
                        len = temps.length;
                        for (int j = 0; j < len; j++) {
                            if (temps[j] instanceof BaseZButton){
                                BaseZButton ztTemp = (BaseZButton) temps[j];
                                if (!(ztTemp.getText().contains("显示")||ztTemp.getText().contains("切换"))){
                                    ztTemp.setVisible(!ztTemp.isVisible());
                                }else{
                                	if (ztTemp.getText().contains("显示背景")){
										ztTemp.setText("显示按钮");
										eastPanel01.setBounds(5, 10 + (13 * (30+2)), 320, 25+len);
									}else if (ztTemp.getText().contains("显示按钮")) {
										ztTemp.setText("显示背景");
										eastPanel01.setBounds(5, 5 + (11 * (30+2)), 320, 25+len);
									}
                                    ztTemp.setVisible(ztTemp.isVisible());

                                }
                            }
                        }
                    }
                }else{
                    com.setVisible(!com.isVisible());
                }
            }
		});
		singleThreadPool.shutdown();
	}
	public void changeBackGround() {
		backPanel.setBackground(GameUtil.getBackGroundPreFix((int)(333*1.1), (int)(470*1.1)));
	}
	public void changeBackGroundByPath(String path) {
	    File file = new File(path);
	    if (file.exists()) {
            backPanel.setBackground(GameUtil.getBackGroundPreFix((int)(333*1.1), (int)(470*1.1),path));
        }
	}


	public BaseZButton[] getBts() {
		return bts;
	}

	private void deActiveAll() {
		BaseZButton[] zts = this.getBts();
		BaseZButton btTemp;
		String textTemp;
		for (BaseZButton zt : zts) {
			btTemp = zt;
			if (!btTemp.isExcuteble()) {
				JIntellitype.getInstance().unregisterHotKey(btTemp.getMarkCode());
				btTemp.setEnabled(true);
				textTemp = btTemp.getText();
				btTemp.setText(textTemp.replace(" (已激活)", ""));
			}
		}
	}
}